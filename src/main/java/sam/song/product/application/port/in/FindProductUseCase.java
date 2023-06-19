package sam.song.product.application.port.in;

import java.util.List;
import sam.song.product.common.response.CommonResponse;
import sam.song.product.domain.Product;

public interface FindProductUseCase {

  CommonResponse<Product> find(long id);
  CommonResponse<List<Product>> findAll();


}
