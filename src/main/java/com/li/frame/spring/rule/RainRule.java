package com.li.frame.spring.rule;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Slf4j
@Rule(name = "rain now",description = "demo rule for rain")
public class RainRule {

    @Condition
    public boolean when(@Fact("rain")String rain){
        return rain.equals("fall");
    }

    @Action
    public void then(Facts facts){
        for (org.jeasy.rules.api.Fact<?> fact : facts) {
           log.debug(fact.getName());
        }
        log.info("take umbrella.........");
    }

}
