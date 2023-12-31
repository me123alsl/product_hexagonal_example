package sam.song.product.adapter.out;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.application.port.out.LoadProductPort;
import sam.song.product.application.port.out.RemoveProductPort;
import sam.song.product.application.port.out.SaveProductPort;
import sam.song.product.application.port.out.UpdateProductPort;
import sam.song.product.domain.Product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements LoadProductPort, SaveProductPort, UpdateProductPort,
    RemoveProductPort {

  private final ProductJpaRepository productJpaRepository;

  @Override
  public Optional<ProductJpaEntity> load(Long id) {
    return productJpaRepository.findById(id);
  }

  @Override
  public List<ProductJpaEntity> loadAll() {
    return productJpaRepository.findAll();
  }

  @Override
  public ProductJpaEntity saveProduct(Product product) {
    return productJpaRepository.save(ProductJpaEntity.from(product));
  }

  @Override
  public ProductJpaEntity updateProduct(long id, UpdateProductRequest request) {
    return productJpaRepository.update(id, request);
  }

  @Override
  public ProductJpaEntity updateQuantity(long id, Integer quantity) {
    return productJpaRepository.updateQuantity(id, quantity);
  }

  @Override
  public List<ProductJpaEntity> loadByOption(SearchProductOptionRequest option, Pageable pageable) {
    return productJpaRepository.findAllSearchOption(option, pageable);
  }

  @Override
  public void removeById(long id) {
    productJpaRepository.deleteById(id);
  }
}
