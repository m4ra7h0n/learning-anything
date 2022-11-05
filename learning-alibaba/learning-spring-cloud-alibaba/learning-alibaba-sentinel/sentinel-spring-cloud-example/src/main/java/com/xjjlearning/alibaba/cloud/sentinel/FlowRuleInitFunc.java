package com.xjjlearning.alibaba.cloud.sentinel;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class FlowRuleInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        initFlowRules();
//        initDegradeRule();
    }

    /**
     限流配置
     */
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();

        // Set limit QPS to 20. (20 times per second)
        // rule.setCount(20);
        rule.setCount(1);

        rule.setResource("sayHello"); //和SphU中的名称一致

        // qps模式/thread模式
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);

        //qps流量控制: 匀速排队/直接拒绝/冷启动/冷启动+匀速排队
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        // 调用方限流所有app
        rule.setLimitApp("default");

//        rule.setStrategy(RuleConstant.STRATEGY_CHAIN);
//        rule.setClusterMode(false);

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    //熔断
    private static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("KEY");
        degradeRule.setCount(10);
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        degradeRule.setTimeWindow(10);
        degradeRule.setMinRequestAmount(5);

        rules.add(degradeRule);
        DegradeRuleManager.loadRules(rules);
    }
}
