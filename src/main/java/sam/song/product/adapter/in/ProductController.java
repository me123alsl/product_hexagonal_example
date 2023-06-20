package sam.song.product.adapter.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sam.song.product.adapter.in.request.CreateProductRequest;
import sam.song.product.adapter.in.request.SearchProductOption;
import sam.song.product.adapter.in.request.UpdateProductRequest;
import sam.song.product.application.port.in.CreateProductUseCase;
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


  /**
   * 상품 전체 조회
   * @return 상품 전체 리스트
   */
  @GetMapping("")
  public CommonResponse<?> findProduct() {
    return findProductUseCase.findAll();
  }

  /**
   * 상품 생성
   * @param createProductDto
   * @return 생성된 상품정보
   */
  @PostMapping("")
  public CommonResponse<?> createProduct(
      @Validated @RequestBody CreateProductRequest createProductDto) {
    return createProductUseCase.create(createProductDto);
  }

  /**
   * 상품 수정
   * @param id
   * @param updateProductDto
   * @return 수정된 상품정보
   */
  @PutMapping("/{id}")
  public CommonResponse updateProduct(
      @PathVariable Long id,
      @Validated @RequestBody UpdateProductRequest updateProductDto) {
    return updateProductUseCase.update(id, updateProductDto);
  }


  /**
   * 상품 조회
   * @param id
   * @return 조회된 상품
   */
  @GetMapping("/{id}")
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
  public CommonResponse<?> searchProduct(
      @RequestBody SearchProductOption searchProductOption, Pageable pageable) {
    return findProductUseCase.findByOption(searchProductOption, pageable);
  }
}
