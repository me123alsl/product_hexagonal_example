package sam.song.product.common.logTrace;

public interface LogTrace {

  TraceStatus begin(String message);
  void end(TraceStatus status);
  void exception(TraceStatus status, Exception e);

  void printMessage(String message);

}
