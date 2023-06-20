package sam.song.product.adapter.out;

import java.util.List;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;

public interface ProductCustomRepository {

  List<ProductJpaEntity> findAllSearchOption(SearchProductOptionRequest option, Pageable pageable);

}
