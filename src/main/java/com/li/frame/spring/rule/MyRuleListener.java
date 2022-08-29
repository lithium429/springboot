package com.li.frame.spring.rule;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;

@Slf4j
public class MyRuleListener implements RuleListener {

    @Override
    public boolean beforeEvaluate(Rule rule, Facts facts) {
        log.info("before evaluate do......");
        return RuleListener.super.beforeEvaluate(rule, facts);
    }
}
