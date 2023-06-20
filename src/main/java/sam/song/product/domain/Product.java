package sam.song.product.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sam.song.product.adapter.in.request.CreateProductRequest;
import sam.song.product.adapter.out.ProductJpaEntity;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Product {

  private long id;
  private String name;
  private int price;
  private int quantity;
  private String description;

   public static Product from(CreateProductRequest request) {
     return Product.builder()
         .name(request.getName())
         .price(request.getPrice())
         .quantity(request.getQuantity())
         .description(request.getDescription())
         .build();
   }

  public static Product from(ProductJpaEntity entity) {
    return Product.builder()
        .id(entity.getId())
        .name(entity.getName())
        .price(entity.getPrice())
        .quantity(entity.getQuantity())
        .description(entity.getDescription())
        .build();
  }

  public static List<Product> from(List<ProductJpaEntity> entities) {
    return entities.stream()
        .map(Product::from)
        .toList();
  }
}
