package sam.song.product.adapter.out;

import java.util.List;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.in.request.UpdateProductRequest;

public interface ProductCustomRepository {

  List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option, Pageable pageable);

  ProductJpaEntity update(long id, UpdateProductRequest request);

  ProductJpaEntity updateQuantity(long id, Integer quantity);
}
