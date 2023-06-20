package sam.song.product.application.port.in;

import java.util.List;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.common.response.CommonResponse;
import sam.song.product.domain.Product;

public interface FindProductUseCase {

  CommonResponse<Product> find(long id);
  CommonResponse<List<Product>> findAll();

  CommonResponse<List<Product>> findByOption(SearchProductOptionRequest option, Pageable pageable);

}
