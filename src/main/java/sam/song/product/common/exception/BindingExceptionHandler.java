package sam.song.product.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sam.song.product.common.response.ErrorResponse;

@RestControllerAdvice
public class BindingExceptionHandler {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> handleException(HttpServletRequest request, MethodArgumentNotValidException e) {
    String errorMessage = e.getBindingResult().getFieldErrors().stream().
        map(fe -> "[" + fe.getField() + "] " + fe.getDefaultMessage()).collect(Collectors.joining(", "));
    ErrorResponse response = ErrorResponse.builder()
        .status(400)
        .errorMessage(errorMessage)
        .path(ExceptionUtils.getPath(request))
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> handleException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
    ErrorResponse response = ErrorResponse.builder()
        .status(400)
        .errorMessage(e.getMessage())
        .path(ExceptionUtils.getPath(request))
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> handleException(HttpServletRequest request, BindException e) {
    ErrorResponse response =  ErrorResponse.builder()
        .status(400)
        .errorMessage(e.getMessage())
        .path(ExceptionUtils.getPath(request))
        .exceptionName(e.getClass().getName())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }


}
