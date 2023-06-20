package sam.song.product.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ErrorResponse<T> extends CommonResponse<T>{
  private String path;
  private String errorMessage;
  private String exceptionName;

  public static ErrorResponse error(int statusCode, String message, String path, String errorMessage, String exceptionName){
    return ErrorResponse.builder()
        .status(500)
        .message("error")
        .path(path)
        .errorMessage(errorMessage)
        .exceptionName(exceptionName)
        .build();
  }
}
