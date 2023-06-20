package sam.song.product.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sam.song.product.common.logTrace.LogTrace;
import sam.song.product.common.logTrace.TraceStatus;

@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

  @Autowired
  private final LogTrace logTrace;

  @Pointcut("execution(* sam.song.product.adapter..*(..))")
  public void controllerAdvice() {
  }

  @Around("controllerAdvice()")
  public Object around(ProceedingJoinPoint jp) throws Throwable {
    TraceStatus status = logTrace.begin(jp.getSignature().getName());
    try {
      Object proceed = jp.proceed();
      logTrace.end(status);
      return proceed;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }


}
