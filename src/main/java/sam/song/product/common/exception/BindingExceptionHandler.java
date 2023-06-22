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
        map(fe -> "[" + fe.getField() + "="+ fe.getRejectedValue() + "] " + fe.getDefaultMessage()).collect(Collectors.joining(", "));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        ErrorResponse.error(400, null, ExceptionUtils.getPath(request), errorMessage, e.getClass().getName())
    );
  }

  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> handleException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        ErrorResponse.error(400, null, ExceptionUtils.getPath(request), e.getMessage(), e.getClass().getName())
    );
  }

  @ExceptionHandler(value = BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> handleException(HttpServletRequest request, BindException e) {

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        ErrorResponse.error(400, null, ExceptionUtils.getPath(request), e.getMessage(), e.getClass().getName())
    );
  }


}
