package sam.song.product.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import sam.song.product.common.logTrace.LogTrace;
import sam.song.product.common.logTrace.TraceId;
import sam.song.product.common.logTrace.TraceStatus;

@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

  private final LogTrace logTrace;

  @Pointcut("execution(* sam.song.product.adapter..*(..))")
  public void controllerAdvice() {
  }

  @Around("controllerAdvice()")
  public void around(ProceedingJoinPoint jp) throws Throwable {
    TraceStatus status = logTrace.begin(jp.getSignature().getName());
    try {
      jp.proceed();
      logTrace.end(status);
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }


}
