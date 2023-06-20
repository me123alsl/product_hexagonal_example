package sam.song.product.adapter.in.request;

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

  @Size(min = 1, max = 100)
  @NotBlank(message = "invalid name")
  private String name;

  @Min(value = 0, message = "invalid price")
  private int price;

  @Min(value  =100 , message = "invalid quantity")
  private int quantity;

  @Size(min = 1, max = 100, message = "invalid description")
  private String description;

}
