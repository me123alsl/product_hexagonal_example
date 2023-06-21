package sam.song.product.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.in.request.UpdateProductRequest;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

  private final JPAQueryFactory queryFactory;
  private final EntityManager entityManager;

  @Override
  public List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option,
      Pageable pageable) {
    return queryFactory.
        selectFrom(QProductJpaEntity.productJpaEntity).
        where(
            nameLike(option.getName()),
            priceGoe(option.getMinPrice()),
            priceLoe(option.getMaxPrice()),
            stockQuantityGoe(option.getMinQuantity()),
            stockQuantityLoe(option.getMaxQuantity())
        ).
        offset(pageable.getOffset()).
        limit(pageable.getPageSize()).
        fetch();
  }

  @Override
  public ProductJpaEntity update(long id, UpdateProductRequest request) {
    JPAUpdateClause clause = queryFactory.update(QProductJpaEntity.productJpaEntity);
    if (request.getName() != null) {
      clause.set(QProductJpaEntity.productJpaEntity.name, request.getName());
    }
    if (request.getPrice() != null) {
      clause.set(QProductJpaEntity.productJpaEntity.price, request.getPrice());
    }
    if (request.getQuantity() != null) {
      clause.set(QProductJpaEntity.productJpaEntity.quantity, request.getQuantity());
    }
    if (request.getDescription() != null) {
      clause.set(QProductJpaEntity.productJpaEntity.description, request.getDescription());
    }
    long affectedRows = clause.set(QProductJpaEntity.productJpaEntity.updatedAt, Instant.now()).
        where(QProductJpaEntity.productJpaEntity.id.eq(id)).
        execute();

    entityManager.flush();
    entityManager.clear();

    return affectedRows == 1 ? queryFactory.selectFrom(QProductJpaEntity.productJpaEntity).
        where(QProductJpaEntity.productJpaEntity.id.eq(id)).fetchOne() : null;
  }

  @Override
  public ProductJpaEntity updateQuantity(long id, Integer quantity) {
    long execute = queryFactory.update(QProductJpaEntity.productJpaEntity).
        set(QProductJpaEntity.productJpaEntity.quantity, quantity).
        set(QProductJpaEntity.productJpaEntity.updatedAt, Instant.now()).
        where(QProductJpaEntity.productJpaEntity.id.eq(id)).
        execute();
    entityManager.flush();
    entityManager.clear();
    return execute == 1 ? queryFactory.selectFrom(QProductJpaEntity.productJpaEntity).
        where(QProductJpaEntity.productJpaEntity.id.eq(id)).setFlushMode(FlushModeType.AUTO)
        .fetchOne() : null;
  }

  private BooleanExpression nameLike(String name) {
    return name != null ? QProductJpaEntity.productJpaEntity.name.like(name) : null;
  }

  private BooleanExpression priceGoe(Integer minPrice) {
    return minPrice != null ? QProductJpaEntity.productJpaEntity.price.goe(minPrice) : null;
  }

  private BooleanExpression priceLoe(Integer maxPrice) {
    return maxPrice != null ? QProductJpaEntity.productJpaEntity.price.loe(maxPrice) : null;
  }

  private BooleanExpression stockQuantityGoe(Integer minQuantity) {
    return minQuantity != null ? QProductJpaEntity.productJpaEntity.quantity.goe(minQuantity)
        : null;
  }

  private BooleanExpression stockQuantityLoe(Integer maxQuantity) {
    return maxQuantity != null ? QProductJpaEntity.productJpaEntity.quantity.loe(maxQuantity)
        : null;
  }
}
