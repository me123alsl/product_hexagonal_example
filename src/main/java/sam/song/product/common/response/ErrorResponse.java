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

}
