package com.gl.rewardservice.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(
            pointcut = "execution(* com.gl.app.service.*.*(..))",
            throwing = "exception"
    )
    public void logException(Exception exception) {
        LOGGER.error("Exception occurred: ", exception);
    }
}