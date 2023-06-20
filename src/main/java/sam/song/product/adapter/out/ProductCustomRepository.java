package sam.song.product.adapter.out;

import java.util.List;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOption;
import sam.song.product.adapter.out.ProductJpaEntity;

public interface ProductCustomRepository {

  List<ProductJpaEntity> findAllSearchOption(SearchProductOption option, Pageable pageable);

}
