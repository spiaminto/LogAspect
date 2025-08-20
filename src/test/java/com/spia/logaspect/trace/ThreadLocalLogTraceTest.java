package com.spia.logaspect.trace;

import com.spia.logaspect.config.AutoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AutoConfig.class)
class ThreadLocalLogTraceTest {

    @Autowired LogTrace logTrace;

    @Test
    void exception() {
        TraceStatus traceStatus = logTrace.begin("message");
        logTrace.exception(traceStatus, new IllegalArgumentException("test"), new Object[1]);
    }
}