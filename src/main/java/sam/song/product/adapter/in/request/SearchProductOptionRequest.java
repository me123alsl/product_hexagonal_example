package sam.song.product.adapter.in.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchProductOptionRequest {

  String name;
  Integer minPrice;
  Integer maxPrice;
  Integer minQuantity;
  Integer maxQuantity;
}
