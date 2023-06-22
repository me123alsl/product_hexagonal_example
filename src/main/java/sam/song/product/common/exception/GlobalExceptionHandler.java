package sam.song.product.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import sam.song.product.common.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = NotFoundProductException.class)
  protected ResponseEntity<?> handleException(NotFoundProductException e) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        ErrorResponse.error(204, null, null, e.getMessage(), e.getClass().getName())
    );
  }

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  protected ResponseEntity<?> handleException(HttpServletRequest request,
      HttpMessageNotReadableException e) {
    String errorMessage = "invalid request body format:" + e.getMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        ErrorResponse.error(400, null, ExceptionUtils.getPath(request), errorMessage,
            e.getClass().getName())
    );
  }

  @ExceptionHandler(value = NoHandlerFoundException.class)
  protected ResponseEntity<?> handleException(NoHandlerFoundException e) {
    String errorPath = e.getHttpMethod() + " " + e.getRequestURL();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        ErrorResponse.error(404, null, errorPath, e.getMessage(), e.getClass().getName())
    );
  }

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<?> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        ErrorResponse.error(500, null, null, e.getMessage(), e.getClass().getName())
    );
  }

}
