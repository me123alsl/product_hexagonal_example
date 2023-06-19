package sam.song.product.application.port.in;

import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.common.response.CommonResponse;

public interface UpdateProductUseCase {

  CommonResponse update(Long id, UpdateProductRequest request);

}
