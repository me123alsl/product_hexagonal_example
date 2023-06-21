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

  @Size(min = 1, max = 10, message = "invalid name")
  @NotBlank(message = "invalid name")
  private String name;

  @Min(value = 100, message = "invalid price")
  private Integer price;

  @Min(value = 100, message = "invalid quantity")
  private Integer quantity;

  @Size(min = 1, max = 1000, message = "invalid description")
  private String description;
}
