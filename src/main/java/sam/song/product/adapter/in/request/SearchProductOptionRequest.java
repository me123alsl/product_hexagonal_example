package sam.song.product.adapter.in.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class SearchProductOptionRequest {

  @Parameter(name = "name", description = "상품명" ,required = false)
  String name;
  @Parameter(name = "minPrice", description = "최소 상품가격",required = false)
  Integer minPrice;
  @Parameter(name = "maxPrice", description = "최대 상품가격" ,required = false)
  Integer maxPrice;
  @Parameter(name = "minQuantity", description = "최소 상품수량" ,required = false)
  Integer minQuantity;
  @Parameter(name = "maxQuantity", description = "최대 상품수량" ,required = false)
  Integer maxQuantity;
}
