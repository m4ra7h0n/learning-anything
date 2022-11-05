package com.xjjlearning.alibaba.cloud.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.sun.glass.ui.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ParamFlowQpsDemo implements CommandLineRunner {

    private static final String NAME = "xjj";
    private static final String  AGE = "22";
    private static final String SEX = "boy";

    /**
     * Here we prepare different parameters to validate flow control by parameters.
     */

    private static final String[] PARAMS = new String[] {NAME, AGE, SEX};

    private static final String RESOURCE_KEY = "sayHelloTest";

    public static void main(String[] args) {
        SpringApplication.run(ParamFlowQpsDemo.class);
    }

    @Autowired
    ParamFlowQpsRunner paramFlowQpsRunner;

    @Override
    public void run(String... args) throws Exception {
        initParamFlowRules();
    }

    private static void initParamFlowRules() {
        // QPS mode, threshold is 5 for every frequent "hot spot" parameter in index 0 (the first arg).
        ParamFlowRule rule = new ParamFlowRule(RESOURCE_KEY)
                .setParamIdx(1) // 设置第几个参数生效
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                .setDurationInSec(3)
//                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
//                .setMaxQueueingTimeMs(600)
                .setCount(10);

        ParamFlowItem nameControl = new ParamFlowItem().setObject(NAME)
                .setClassType(String.class.getTypeName())
                .setCount(1);
        ParamFlowItem ageControl = new ParamFlowItem().setObject(AGE)
                .setClassType(String.class.getTypeName())
                .setCount(1);
        ParamFlowItem sexControl = new ParamFlowItem().setObject(SEX)
                .setClassType(String.class.getTypeName())
                .setCount(1);

        List<ParamFlowItem> ites = new ArrayList<>();
        ites.add(nameControl);
        ites.add(ageControl);
        ites.add(sexControl);

        rule.setParamFlowItemList(ites);
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
    }

}
