package sam.song.product.application.port.in;

import java.util.List;
import org.springframework.data.domain.Pageable;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.common.response.CommonResponse;
import sam.song.product.domain.Product;

public interface FindProductUseCase {

  /**
   * Find product by id
   * @param id
   * @return CommonResponse<Product>
   */
  CommonResponse<Product> find(long id);

  /**
   * Find all products
   * @return CommonResponse<List<Product>>
   */
  CommonResponse<List<Product>> findAll();

  /**
   * Find products by option
   * @param option
   * @param pageable
   * @return CommonResponse
   */
  CommonResponse<List<Product>> findByOption(SearchProductOptionRequest option, Pageable pageable);

}
