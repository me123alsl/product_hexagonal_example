package sam.song.product.application.port.out;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.out.ProductJpaEntity;

public interface LoadProductPort {

  Optional<ProductJpaEntity> load(Long id);
  List<ProductJpaEntity> loadAll();
  List<ProductJpaEntity> loadByOption(SearchProductOptionRequest option, Pageable pageable);
}
