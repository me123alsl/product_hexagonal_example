package sam.song.product.application.port.in;

import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.common.response.CommonResponse;

public interface UpdateProductUseCase {

  /**
   * Update product by id
   * @param id
   * @param request
   * @return CommonResponse
   */
  CommonResponse update(Long id, UpdateProductRequest request);

  CommonResponse minusQuantity(Long id, Integer quantity);
}
