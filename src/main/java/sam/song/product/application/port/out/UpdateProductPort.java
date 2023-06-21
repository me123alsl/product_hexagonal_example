package sam.song.product.application.port.out;

import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.adapter.out.ProductJpaEntity;
import sam.song.product.adapter.out.QProductJpaEntity;
import sam.song.product.domain.Product;

public interface UpdateProductPort {

  ProductJpaEntity updateProduct(long id, UpdateProductRequest request);
  ProductJpaEntity updateQuantity(long id, Integer quantity);
}
