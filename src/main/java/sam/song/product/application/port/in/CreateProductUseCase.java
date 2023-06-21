package sam.song.product.application.port.in;

import sam.song.product.adapter.in.request.CreateProductRequest;
import sam.song.product.common.response.CommonResponse;

public interface CreateProductUseCase {

  /**
   * Create Product by request
   * @param request
   * @return CommonResponse
   */
  CommonResponse create(CreateProductRequest request);

}
