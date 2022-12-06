package com.xjjlearning.spring.cloud.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class GatewayConfiguration {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers;
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter(){
        return new SentinelGatewayFilter(); //全局过滤器不需要修改配置文件
    }

    @PostConstruct
    public void doInit(){
        GatewayCallbackManager.setBlockHandler(new GpSentinelGatewayBlockExceptionHandler());
        initCustomizedApis();
        initGatewayRules();
    }

    // sentinel
    private void initGatewayRules(){
        Set<GatewayFlowRule> rules=new HashSet<>();

        rules.add(new GatewayFlowRule("spring-cloud-gateway-nacos-provider")
                .setCount(1).
                setIntervalSec(1));

        rules.add(new GatewayFlowRule("first_customized_api").
                setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME).
                setCount(1).
                setIntervalSec(1));

        GatewayRuleManager.loadRules(rules);
    }

    private void initCustomizedApis(){
       Set<ApiDefinition> definitions=new HashSet<>();
       ApiDefinition apiDefinition=new ApiDefinition("first_customized_api");
       apiDefinition.setPredicateItems(new HashSet<ApiPredicateItem>(){{
           add(new ApiPathPredicateItem().setPattern("/foo/**"));
           add(new ApiPathPredicateItem().setPattern("/baz/**")
                   .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
       }});
       definitions.add(apiDefinition);
       GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }
}
