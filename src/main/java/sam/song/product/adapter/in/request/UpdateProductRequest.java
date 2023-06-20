package sam.song.product.adapter.in.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class UpdateProductRequest {

  @Size(min = 1, max = 10)
  @NotBlank
  private String name;

  @Min(value = 0)
  private Integer price;

  @Min(100)
  private Integer quantity;

  @Size(min = 1, max = 100)
  private String description;
}
