package sam.song.product.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import sam.song.product.domain.Product;

@Entity
@Getter
@RequiredArgsConstructor
@DynamicUpdate
@Table(name = "product")
public class ProductJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name")
  private String name;

  @Column(name = "price")
  private int price;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "description")
  private String description;

  @CreationTimestamp
  @Column(name = "created_at")
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Instant updatedAt;

  @Builder
  public ProductJpaEntity(long id, String name, int price, int quantity, String description) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.description = description;
  }

  public static ProductJpaEntity from(Product product) {
    return ProductJpaEntity.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .description(product.getDescription())
        .build();
  }
}
