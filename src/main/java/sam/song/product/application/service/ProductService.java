package sam.song.product.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sam.song.product.adapter.in.request.CreateProductRequest;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.adapter.out.ProductJpaEntity;
import sam.song.product.application.port.in.CreateProductUseCase;
import sam.song.product.application.port.in.DeleteProductUseCase;
import sam.song.product.application.port.in.FindProductUseCase;
import sam.song.product.application.port.in.UpdateProductUseCase;
import sam.song.product.application.port.out.LoadProductPort;
import sam.song.product.application.port.out.RemoveProductPort;
import sam.song.product.application.port.out.SaveProductPort;
import sam.song.product.application.port.out.UpdateProductPort;
import sam.song.product.common.exception.NotFoundProductException;
import sam.song.product.common.response.CommonResponse;
import sam.song.product.domain.Product;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements FindProductUseCase, CreateProductUseCase,
    UpdateProductUseCase, DeleteProductUseCase {

  private final LoadProductPort loadProductPort;
  private final SaveProductPort saveProductPort;
  private final UpdateProductPort updateProductPort;
  private final RemoveProductPort removeProductPort;

  @Override
  @Transactional(timeout = 5, readOnly = false)
  public CommonResponse create(CreateProductRequest request) {
    ProductJpaEntity productJpaEntity = saveProductPort.saveProduct(Product.from(request));
    Product saveProduct = Product.from(productJpaEntity);
    return CommonResponse.success(201, "Product created successfully", saveProduct);
  }

  @Override
  public CommonResponse<Product> find(long id) {
    ProductJpaEntity findProductEntity = loadProductPort.load(id)
        .orElseThrow(() -> new NotFoundProductException("Product not found"));
    Product product = Product.from(findProductEntity);
    return CommonResponse.success(200, "Product found successfully", product);
  }

  @Override
  public CommonResponse<List<Product>> findAll() {
    List<ProductJpaEntity> productJpaEntities = loadProductPort.loadAll();
    List<Product> products = Product.from(productJpaEntities);
    if (products.isEmpty()) {
      throw new NotFoundProductException("Product not found");
    }
    return CommonResponse.success(200, "Product found successfully", products);
  }

  @Override
  public CommonResponse<List<Product>> findByOption(SearchProductOptionRequest option,
      Pageable pageable) {
    List<Product> products = Product.from(loadProductPort.loadByOption(option, pageable));
    if (products.isEmpty()) {
      throw new NotFoundProductException("Product not found");
    }
    return CommonResponse.success(200, "Product found successfully", products);
  }

  @Override
  @Transactional(timeout = 5, readOnly = false)
  public CommonResponse<?> delete(Long id) {
    ProductJpaEntity findEntity = loadProductPort.load(id)
        .orElseThrow(() -> new NotFoundProductException("Product not found"));
    removeProductPort.removeById(findEntity.getId());
    return CommonResponse.success(200, "Product deleted successfully", null);
  }

  @Override
  @Transactional(timeout = 5, readOnly = false)
  public CommonResponse update(Long id, UpdateProductRequest request) {
    ProductJpaEntity findEntity = loadProductPort.load(id)
        .orElseThrow(() -> new NotFoundProductException("Product not found"));
    Product updatedProduct = Product.from(updateProductPort.updateProduct(id, request));
    return CommonResponse.success(200, "Product updated successfully", updatedProduct);
  }

  @Override
  @Transactional(timeout = 5, readOnly = false)
  public CommonResponse minusQuantity(Long id, Integer quantity) {
    ProductJpaEntity findEntity = loadProductPort.load(id)
        .orElseThrow(() -> new NotFoundProductException("Product not found"));
    Product findProduct = Product.from(findEntity);
    findProduct.minusQuantity(quantity);
    ProductJpaEntity updatedEntity = updateProductPort.updateQuantity(findProduct.getId(),
        findProduct.getQuantity());
    Product resultProduct = Product.from(updatedEntity);
    return CommonResponse.success(200, "Product updated successfully", resultProduct);
  }


}