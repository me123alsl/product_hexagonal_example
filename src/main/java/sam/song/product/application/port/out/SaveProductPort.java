package sam.song.product.application.port.out;

import sam.song.product.adapter.out.ProductJpaEntity;
import sam.song.product.domain.Product;

public interface SaveProductPort {

  ProductJpaEntity saveProduct(Product product);

}
