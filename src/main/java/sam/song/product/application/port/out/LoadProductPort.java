package sam.song.product.application.port.out;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOption;
import sam.song.product.adapter.out.ProductJpaEntity;

public interface LoadProductPort {

  Optional<ProductJpaEntity> load(Long id);
  List<ProductJpaEntity> loadAll();
  boolean existsById(Long id);
  List<ProductJpaEntity> loadByOption(SearchProductOption option, Pageable pageable);
}
