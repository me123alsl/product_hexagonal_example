package sam.song.product.adapter.in;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sam.song.product.adapter.in.request.CreateProductRequest;
import sam.song.product.adapter.in.request.SearchProductOptionRequest;
import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.application.port.in.CreateProductUseCase;
import sam.song.product.application.port.in.DeleteProductUseCase;
import sam.song.product.application.port.in.FindProductUseCase;
import sam.song.product.application.port.in.UpdateProductUseCase;
import sam.song.product.common.response.CommonResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final CreateProductUseCase createProductUseCase;
  private final FindProductUseCase findProductUseCase;
  private final UpdateProductUseCase updateProductUseCase;
  private final DeleteProductUseCase deleteProductUseCase;

  /**
   * 상품 전체 조회
   * @return 상품 전체 리스트
   */
  @GetMapping("")
  public CommonResponse<?> findProduct() throws InterruptedException {
    return findProductUseCase.findAll();
  }

  /**
   * 상품 생성
   * @param createProductDto
   * @return 생성된 상품정보
   */
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public CommonResponse<?> createProduct(
      @RequestBody @Valid CreateProductRequest createProductDto) {
    return createProductUseCase.create(createProductDto);
  }

  /**
   * 상품 수정
   * @param id
   * @param updateProductDto
   * @return 수정된 상품정보
   */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse updateProduct(
      @PathVariable Long id,
      @RequestBody @Validated UpdateProductRequest updateProductDto) {
    return updateProductUseCase.update(id, updateProductDto);
  }


  /**
   * 상품 조회
   * @param id
   * @return 조회된 상품
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> findProductById(
      @PathVariable Long id) {
    return findProductUseCase.find(id);
  }

  /**
   * 상품 조건 검색
   * @param searchProductOption
   * @return 조회된 상품 리스트
   */
  @PostMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> searchProduct(
      @RequestBody SearchProductOptionRequest searchProductOption, Pageable pageable) {
    return findProductUseCase.findByOption(searchProductOption, pageable);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> deleteProduct(
      @PathVariable Long id) {
    return deleteProductUseCase.delete(id);
  }

  @PutMapping("/{id}/sell/count/{quantity}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> sellProduct(
      @PathVariable Long id,
      @PathVariable Integer quantity) {
    return updateProductUseCase.minusQuantity(id, quantity);
  }
}
