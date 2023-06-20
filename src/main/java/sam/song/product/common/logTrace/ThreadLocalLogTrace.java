package sam.song.product.common.logTrace;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class ThreadLocalLogTrace implements LogTrace{
  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EXCEPTION_PREFIX = "<x-";

  private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<TraceId>();

  public TraceStatus begin(String message) {
    syncTraceId();
    TraceId traceId = traceIdHolder.get();
    Long startTimeMs = System.currentTimeMillis();
    /* Log show */
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  private void syncTraceId(){
    TraceId traceId = traceIdHolder.get();
    if (traceId == null) {
      traceIdHolder.set(new TraceId());
    } else {
      traceIdHolder.set(traceId.createNextId());
    }
  }
  public void end(TraceStatus status){
    complete(status, null);
  }

  public void exception(TraceStatus status, Exception e){
    complete(status, e);
  }

  @Override
  public void printMessage(String message) {
    TraceId traceId = traceIdHolder.get();
    log.debug("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
  }

  private void complete(TraceStatus status, Exception e){
    Long endTimeMs = System.currentTimeMillis();
    long resultTimeMs = endTimeMs - status.getStartTimeMs();
    TraceId traceId = status.getTraceId();
    String message = status.getMessage();
    /* Log show */
    if(e == null){
      log.info("[{}] {}{} (elapse={}ms)",
          traceId.getId(),
          addSpace(COMPLETE_PREFIX, traceId.getLevel()),
          message, resultTimeMs);
    }else{
      log.info("[{}] {}{} (elapse={}ms) (exName={}, message={})",
          traceId.getId(),
          addSpace(EXCEPTION_PREFIX, traceId.getLevel()),
          message, resultTimeMs,
          e.getClass().getSimpleName(), e.getMessage());
    }

    releaseTraceId();
  }

  private void releaseTraceId(){
    TraceId traceId = traceIdHolder.get();
    if (traceId.isFirstId()) {
      traceIdHolder.remove();
    } else {
      traceIdHolder.set(traceId.createPrevId());
    }
  }
  private static String addSpace(String prefix, int level){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < level; i++){
      sb.append(i == level -1  ? "|" + prefix : "|  ");
    }
    return sb.toString();
  }
}
