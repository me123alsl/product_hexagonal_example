package sam.song.product.adapter.in.request;

import io.swagger.v3.oas.annotations.Parameter;
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

  @Parameter(name = "name", description = "상품명", required = false)
  @Size(min = 1, max = 10, message = "invalid name")
  @NotBlank(message = "invalid name")
  private String name;

  @Parameter(name = "price", description = "상품가격", required = false)
  @Min(value = 100, message = "invalid price")
  private Integer price;

  @Parameter(name = "quantity", description = "상품수량", required = false)
  @Min(value = 100, message = "invalid quantity")
  private Integer quantity;

  @Parameter(name = "description", description = "상품설명", required = false)
  @Size(min = 1, max = 1000, message = "invalid description")
  private String description;
}
