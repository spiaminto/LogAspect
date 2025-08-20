package com.spia.logaspect;

import com.spia.logaspect.trace.LogTrace;
import com.spia.logaspect.trace.TraceId;
import com.spia.logaspect.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class LogTraceAspect {

    private final LogTrace logTrace;

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        Object[] params = new Object[1];
        String message = "Aspect Start";
        try {
            message = joinPoint.getSignature().toShortString();
            message = message.substring(0, message.indexOf("("));

            params = joinPoint.getArgs();

            status = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Throwable t) {
            if (status == null) {
                log.warn("[execute()] TraceStatus is null");
                status = new TraceStatus(new TraceId(), message);
            }
            logTrace.exception(status, t, params);
            throw t; //예외를 처리하진 않음
        }
    }

}