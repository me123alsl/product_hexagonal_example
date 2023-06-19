package sam.song.product.application.port.out;

import java.util.List;
import java.util.Optional;
import sam.song.product.adapter.out.ProductJpaEntity;

public interface LoadProductPort {

  Optional<ProductJpaEntity> load(long id);
  List<ProductJpaEntity> loadAll();
  boolean existsById(long id);
}
