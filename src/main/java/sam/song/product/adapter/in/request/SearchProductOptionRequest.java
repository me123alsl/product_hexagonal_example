package sam.song.product.adapter.in.request;

import lombok.Getter;

@Getter
public class SearchProductOptionRequest {

  String name;
  Integer minPrice;
  Integer maxPrice;
  Integer minQuantity;
  Integer maxQuantity;
}
