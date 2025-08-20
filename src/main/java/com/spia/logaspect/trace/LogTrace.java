package com.spia.logaspect.trace;

import lombok.NonNull;

public interface LogTrace {
    /**
     * joinPoint.proceed 전에 로깅 (-> 방향)
     * @param message
     * @return
     */
    TraceStatus begin(String message);

    /**
     * joinPoint.proceed 후에 로깅 (<- 방향)
     * @param status
     */
    void end(TraceStatus status);

    /**
     * 예외 발생시 로깅
     * @param status
     * @param t
     * @param params
     */
    void exception(@NonNull TraceStatus status, Throwable t, @NonNull Object[] params);
}
