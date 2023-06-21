package sam.song.product.adapter.in.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CreateProductRequest {

  @Parameter(name = "name", description = "상품명", required = true)
  @Size(min = 1, max = 100)
  @NotBlank(message = "invalid name")
  private String name;

  @Parameter(name = "price", description = "상품가격", required = true)
  @Min(value = 100, message = "invalid price")
  private int price;

  @Parameter(name = "quantity", description = "상품수량", required = true)
  @Min(value = 100, message = "invalid quantity")
  private int quantity;

  @Parameter(name = "description", description = "상품설명", required = true)
  @Size(min = 1, max = 1000, message = "invalid description")
  private String description;

}
