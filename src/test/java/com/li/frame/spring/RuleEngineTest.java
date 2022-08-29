package com.li.frame.spring;

import com.li.frame.spring.rule.MyRuleListener;
import com.li.frame.spring.rule.RainRule;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.junit.jupiter.api.Test;

public class RuleEngineTest {

    @Test
    public void demoRule(){
        Rules rules=new Rules();
        rules.register(new RainRule());

        Facts facts=new Facts();
        facts.put("rain","fall");

        RulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
    }

    @Test
    public void demoRule2() throws Exception {
        Rule rule= new RuleBuilder()
                .name("hello rule")
                .when(facts -> {
                    Fact temp = facts.getFact("temp");
                    return (int)temp.getValue()>30;
                })
                .then(facts -> {
                    System.out.println("high tmp");
                }).build();

        Facts facts=new Facts();
        facts.put("temp",50);

        Rules rules=new Rules();
        rules.register(rule);

        DefaultRulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.registerRuleListener(new MyRuleListener());
        rulesEngine.fire(rules,facts);

    }

    @Test
    public void enumsTest(){

    }
}
