package sam.song.product.adapter.out;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;

public interface ProductJpaRepository
    extends JpaRepository<ProductJpaEntity, Long>, ProductCustomRepository {

  /**
   * 상품 전체 조회 Repository
   * @return 상품 엔티티 리스트
   */
  List<ProductJpaEntity> findAll();

  /**
   * 상품 단건 조회 Repository
   * @param id must not be {@literal null}.
   * @return 상품 엔티티 (옵셔널)
   */
  Optional<ProductJpaEntity> findById(Long id);

  /**
   * 상품 존재 여부 Repository
   * @param id must not be {@literal null}.
   * @return 상품 존재 여부
   */
  boolean existsById(Long id);

  /**
   * 상품 저장 Repository
   * @param productJpaEntity must not be {@literal null}.
   * @return 저장된 상품 엔티티
   */
  ProductJpaEntity save(ProductJpaEntity productJpaEntity);

  /**
   * 상품 삭제 Repository
   * @param id must not be {@literal null}.
   */
  @Override
  void deleteById(Long id);

  /**
   * 상품 조건 조회 Repository
   * @param option
   * @param pageable
   * @return 상품엔티티 리스트
   */
  @Override
  List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option, Pageable pageable);

}
