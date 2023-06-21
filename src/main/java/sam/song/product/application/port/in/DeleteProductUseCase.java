package sam.song.product.application.port.in;

import sam.song.product.common.response.CommonResponse;

public interface DeleteProductUseCase {

  /**
   * Delete product by id
   * @param id
   * @return CommonResponse
   */
  CommonResponse<?> delete(Long id);
}
