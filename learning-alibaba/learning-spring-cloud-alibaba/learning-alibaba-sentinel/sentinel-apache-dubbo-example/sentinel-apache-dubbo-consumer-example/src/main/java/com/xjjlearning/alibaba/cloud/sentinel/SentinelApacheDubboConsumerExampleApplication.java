package com.xjjlearning.alibaba.cloud.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.RpcException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.springframework.boot.CommandLineRunner;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDubbo
@Slf4j
public class SentinelApacheDubboConsumerExampleApplication implements CommandLineRunner{
    static {
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO); //源码老是warn打印异常  给他去掉
    }
    private static final String INTERFACE_RES_KEY = IHelloService.class.getName();
    private static final String RES_KEY = INTERFACE_RES_KEY + ":sayHello(java.lang.String)";

    public static void main(String[] args) {
        SpringApplication.run(SentinelApacheDubboConsumerExampleApplication.class, args);
    }

    @Reference(check = false, mock = "com.xjjlearning.alibaba.cloud.sentinel.MockHelloService")
    private IHelloService helloService;

    @Override
    public void run(String... args) {
        //超过每秒5次返回mock, 降级服务
        initFlowRule(10, true);
        for (int i = 0; i < 15; i++) {
            try {
                String message = helloService.sayHello("xjj");
                System.out.println("Success: " + message);
            } catch (SentinelRpcException ex) {
                System.out.println("Blocked");
//                if (ex.getMessage().contains("FlowException")) {
//                } else {
//                    ex.printStackTrace();
//                }
            } catch (RpcException ex) {
                ex.printStackTrace();
            }
        }
    }

    // provider/consumer端加都行
    private static void initFlowRule(int interfaceFlowLimit, boolean method) {
        FlowRule flowRule = new FlowRule(INTERFACE_RES_KEY)
                .setCount(interfaceFlowLimit)
                .setGrade(RuleConstant.FLOW_GRADE_QPS);
        List<FlowRule> list = new ArrayList<>();
        if (method) {
            FlowRule flowRule1 = new FlowRule(RES_KEY)
                    .setCount(5)
                    .setGrade(RuleConstant.FLOW_GRADE_QPS);
            list.add(flowRule1);
        }
        list.add(flowRule);
        FlowRuleManager.loadRules(list);
    }

    // 暂时没搞filter  后续再说
//    public static void registryCustomFallback() {
//        DubboAdapterGlobalConfig.setConsumerFallback(
//                (invoker, invocation, ex) -> AsyncRpcResult.newDefaultAsyncResult("fallback", invocation));
//
//    }
}
