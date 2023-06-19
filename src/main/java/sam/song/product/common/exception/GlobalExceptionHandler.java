package sam.song.product.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import sam.song.product.common.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  protected ResponseEntity<?> handleException(HttpServletRequest request,
      HttpMessageNotReadableException e) {
    ErrorResponse response = ErrorResponse.builder()
        .status(400)
        .errorMessage("invalid request body format:" + e.getMessage())
        .path(request.getServletPath())
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }


  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<?> handleException(Exception e) {
    ErrorResponse response = ErrorResponse.builder()
        .status(500)
        .errorMessage(e.getMessage())
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(value = NoHandlerFoundException.class)
  protected ResponseEntity<?> handleException(NoHandlerFoundException e) {
    ErrorResponse response = ErrorResponse.builder()
        .status(404)
        .errorMessage(e.getMessage())
        .path("[" + e.getHttpMethod() + "]" + e.getRequestURL())
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }


}
