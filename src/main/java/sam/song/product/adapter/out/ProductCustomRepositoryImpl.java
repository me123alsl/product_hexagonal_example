package sam.song.product.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository
{
  private final JPAQueryFactory queryFactory;
  @Override
  public List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option, Pageable pageable) {
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
    return minQuantity != null ? QProductJpaEntity.productJpaEntity.quantity.goe(minQuantity) : null;
  }

  private BooleanExpression stockQuantityLoe(Integer maxQuantity) {
    return maxQuantity != null ? QProductJpaEntity.productJpaEntity.quantity.loe(maxQuantity) : null;
  }

}
