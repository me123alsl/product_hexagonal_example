package sam.song.product.adapter.out;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;

public interface ProductJpaRepository
    extends JpaRepository<ProductJpaEntity, Long>, ProductCustomRepository {

  List<ProductJpaEntity> findAll();

  Optional<ProductJpaEntity> findById(Long id);
  boolean existsById(Long id);

  ProductJpaEntity save(ProductJpaEntity productJpaEntity);

  @Override
  void deleteById(Long id);

  List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option, Pageable pageable);

}
