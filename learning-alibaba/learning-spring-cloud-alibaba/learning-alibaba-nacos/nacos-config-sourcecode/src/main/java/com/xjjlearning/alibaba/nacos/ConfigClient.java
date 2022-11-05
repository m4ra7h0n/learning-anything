package com.xjjlearning.alibaba.nacos;

import com.fasterxml.jackson.databind.node.LongNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Slf4j
class ConfigClient {
    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;

    final ScheduledExecutorService executor;
    final ScheduledExecutorService executorService;

    public ConfigClient(String url, String dataId) {
        httpClient = HttpClientBuilder.create().build();
        // ① httpClient 客户端超时时间要大于长轮询约定的超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(40000).build();

        executor = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        executorService = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        executor.scheduleWithFixedDelay(() -> {
            executorService.execute(new LongPollingRunnable(url, dataId));
        }, 1L, 10L, TimeUnit.MILLISECONDS);
    }

    class LongPollingRunnable implements Runnable {
        private final String url;
        private final String dataId;
        public LongPollingRunnable(String url, String dataId) {
            this.url = url;
            this.dataId = dataId;
        }

        @Override
        public void run() {
            try {
                String endpoint = url + "?dataId=" + dataId;
                HttpGet request = new HttpGet(endpoint);
                CloseableHttpResponse response = httpClient.execute(request);
                switch (response.getStatusLine().getStatusCode()) {
                    case 200: {
                        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
                                .getContent()));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            result.append(line);
                        }
                        response.close();
                        String configInfo = result.toString();
                        log.info("dataId: [{}] changed, receive configInfo: {}", dataId, configInfo);
                        executorService.execute(this);
//                        longPolling(url, dataId);
                        break;
                    }
                    // ② 304 响应码标记配置未变更
                    case 304: {
                        log.info("longPolling dataId: [{}] once finished, configInfo is unchanged, longPolling again", dataId);
                        executorService.execute(this);
                        break;
                    }
                    default: {
                        throw new RuntimeException("unExcepted HTTP status code");
                    }
                }
            } catch (IOException e) {
                executorService.schedule(this, 40000, TimeUnit.MILLISECONDS);
                e.printStackTrace();
            }
        }
    }

    static {
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO); //源码老是warn打印异常  给他去掉
    }

    public static void main(String[] args) throws InterruptedException {
//        // httpClient 会打印很多 debug 日志，关闭掉

        String url = "http://127.0.0.1:8080/listener";
        String dataId = "user";
        // ③ 对 dataId: user 进行配置监听
        ConfigClient configClient = new ConfigClient(url, dataId);
        while (true) {
            Thread.sleep(10000);
        }
    }
}
