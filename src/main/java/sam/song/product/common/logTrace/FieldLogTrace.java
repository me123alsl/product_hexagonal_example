package sam.song.product.common.logTrace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace{
  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EXCEPTION_PREFIX = "<x-";

  private TraceId traceIdHolder;

  public TraceStatus begin(String message) {
    syncTraceId();
    TraceId traceId = new TraceId();
    Long startTimeMs = System.currentTimeMillis();
    /* Log show */
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  private void syncTraceId(){
    if (traceIdHolder == null) {
      traceIdHolder = new TraceId();
    } else {
      traceIdHolder = traceIdHolder.createNextId();
    }
  }
  public void end(TraceStatus status){
    complete(status, null);
  }

  public void exception(TraceStatus status, Exception e){
    complete(status, e);
  }

  private void complete(TraceStatus status, Exception e){
    Long endTimeMs = System.currentTimeMillis();
    long resultTimeMs = endTimeMs - status.getStartTimeMs();
    TraceId traceId = status.getTraceId();
    String message = status.getMessage();
    /* Log show */
    if(e == null){
      log.info("[{}] {}{} (elapse={}ms)", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), message, resultTimeMs);
    }else{
      log.info("[{}] {}{} (elapse={}ms) (ex={})", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()), message, resultTimeMs, e.toString());
    }

    releaseTraceId();
  }

  private void releaseTraceId(){
    if (traceIdHolder.isFirstId()) {
      traceIdHolder = null;
    } else {
      traceIdHolder = traceIdHolder.createPrevId();
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
