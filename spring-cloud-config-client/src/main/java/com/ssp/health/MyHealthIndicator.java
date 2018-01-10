package com.ssp.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * 自定义健康检查 down个up
 * @author: sunshaoping
 * @date: Create by in 下午3:51 2018/1/10
 */
public class MyHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        builder.down().withDetail("MyHealthIndicator", "day day down");
//        builder.up().withDetail("MyHealthIndicator", "day day up");
    }
}
