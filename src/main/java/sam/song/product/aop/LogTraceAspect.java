package sam.song.product.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import sam.song.product.common.logTrace.LogTrace;
import sam.song.product.common.logTrace.TraceStatus;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

  private final LogTrace logTrace;

  @Pointcut("execution(* sam.song.product.adapter..*(..)) || execution(* sam.song.product.application..*(..))")
  public void logTracingAdvice() {
  }

  @Around("logTracingAdvice()")
  public Object around(ProceedingJoinPoint jp) throws Throwable {
    TraceStatus status = logTrace.begin(jp.getSignature().toShortString());
    showParameters(jp.getArgs());
    try {
      Object proceed = jp.proceed(jp.getArgs());
      logTrace.end(status);
      return proceed;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }

  private void showParameters(Object []obj){
    for (int i = 0; i < obj.length; i++) {
      logTrace.printMessage("[Parameter #"+i + "] : " + obj[i].toString());
    }
  }

}
