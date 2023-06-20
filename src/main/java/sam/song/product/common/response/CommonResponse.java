package sam.song.product.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

  private int status;
  private String message;
  private T data;

  public static <T> CommonResponse<T> success(int status, String message, T data) {
    return CommonResponse.<T>builder()
        .status(status)
        .message(message)
        .data(data)
        .build();
  }
}
