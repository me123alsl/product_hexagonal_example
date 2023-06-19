package sam.song.product.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.net.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sam.song.product.common.response.ErrorResponse;

@RestControllerAdvice
public class BindingExceptionHandler {

  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<?> handleException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
    ErrorResponse response = ErrorResponse.builder()
        .status(400)
        .errorMessage(e.getMessage())
        .path(request.getServletPath())
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = BindException.class)
  protected ResponseEntity<?> handleException(HttpServletRequest request, BindException e) {
    ErrorResponse response =  ErrorResponse.builder()
        .status(400)
        .errorMessage(e.getMessage())
        .path(request.getServletPath())
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
