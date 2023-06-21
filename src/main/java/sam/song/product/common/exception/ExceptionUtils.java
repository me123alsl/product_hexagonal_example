package sam.song.product.common.exception;

import jakarta.servlet.http.HttpServletRequest;

public class ExceptionUtils {

  static String getPath(HttpServletRequest request) {
    return request.getMethod() + " " + request.getServletPath();
  }

}
