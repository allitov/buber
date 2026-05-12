package io.allitov.buber.notification.aop;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Аспект, ответственный за логирование контроллеров.
 */
@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {

    /**
     * Точка среза для всех контроллеров.
     */
    @Pointcut("within(io.allitov.buber.notification.api.controller..*)")
    public void controllerPointcut() {}

    /**
     * Логирование выполнения метода: запрос, время выполнения и ответ.
     */
    @Around("controllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("Enter: {}.{}() with argument[s] = {}", className, methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            log.info(
                    "Exit: {}.{}() with result = {}. Execution time: {} ms",
                    className,
                    methodName,
                    result,
                    executionTime);
            return result;
        } catch (IllegalArgumentException e) {
            log.info("Illegal argument: {} in {}.{}()", Arrays.toString(args), className, methodName);
            throw e;
        } catch (Throwable e) {
            log.error("Exception in {}.{}(): {}", className, methodName, e.getMessage(), e);
            throw e;
        }
    }
}
