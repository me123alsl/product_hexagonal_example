package sam.song.product.adapter.out;

import java.util.List;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.in.request.UpdateProductRequest;

public interface ProductCustomRepository {

  /**
   * 상품 조건 조회 Repository
   * @param option
   * @param pageable
   * @return 상품엔티티 리스트
   */
  List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option, Pageable pageable);

  /**
   * 상품 수정 Repository
   * @param id
   * @param request
   * @return 수정된 상품 엔티티
   */
  ProductJpaEntity update(long id, UpdateProductRequest request);

  /**
   * 상품 수량 수정 Repository
   * @param id
   * @param quantity
   * @return 수정된 상품 엔티티
   */
  ProductJpaEntity updateQuantity(long id, Integer quantity);
}
