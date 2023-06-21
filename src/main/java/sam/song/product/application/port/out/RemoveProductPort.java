package sam.song.product.application.port.out;

import sam.song.product.domain.Product;

public interface RemoveProductPort {
  void removeById(long id);
}
