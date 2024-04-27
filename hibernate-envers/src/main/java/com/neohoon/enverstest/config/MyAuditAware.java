package com.neohoon.enverstest.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MyAuditAware implements AuditorAware<Long> {

    private static final AtomicLong counter = new AtomicLong();

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(counter.addAndGet(1));
    }
}
