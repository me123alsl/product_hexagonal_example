package sam.song.product.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestParam;
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

@Tag(name = "상품 API", description = "상품 API")
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
   *
   * @return 상품 전체 리스트
   */
  @Operation(summary = "상품 전체 조회", description = "상품 전체 조회", tags = {"상품 API", "조회"})
  @GetMapping("")
  public CommonResponse<?> findProduct() throws InterruptedException {
    return findProductUseCase.findAll();
  }

  /**
   * 상품 생성
   *
   * @param createProductDto
   * @return 생성된 상품정보
   */
  @Operation(summary = "상품 생성", description = "상품 생성", tags = {"상품 API", "생성"})
  @Parameter(in = ParameterIn.DEFAULT, name = "createProductDto", description = "상품 생성 정보", required = true,
      content = @Content(schema = @Schema(implementation = CreateProductRequest.class))
  )
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public CommonResponse<?> createProduct(
      @RequestBody @Valid CreateProductRequest createProductDto) {
    return createProductUseCase.create(createProductDto);
  }

  /**
   * 상품 수정
   *
   * @param id
   * @param updateProductDto
   * @return 수정된 상품정보
   */
  @Operation(summary = "상품 수정", description = "상품 수정", tags = {"상품 API", "수정"})
  @Parameters({
      @Parameter(in=ParameterIn.PATH, name = "id", description = "상품 ID", required = true),
      @Parameter(in=ParameterIn.DEFAULT, name = "updateProductDto", description = "상품 수정 정보", required = true,
          content = @Content(schema = @Schema(implementation = UpdateProductRequest.class))
      )
  })
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse updateProduct(
      @PathVariable Long id,
      @RequestBody @Validated UpdateProductRequest updateProductDto) {
    return updateProductUseCase.update(id, updateProductDto);
  }


  /**
   * 상품 조회
   *
   * @param id
   * @return 조회된 상품
   */
  @Operation(summary = "상품 조회", description = "상품 조회", tags = {"상품 API", "조회"})
  @Parameters({
      @Parameter(in = ParameterIn.PATH, name = "id", description = "상품 ID", required = true)
  })
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> findProductById(
      @PathVariable Long id) {
    return findProductUseCase.find(id);
  }

  /**
   * 상품 조건 검색
   * @param searchProductOption
   * @param pageable
   * @return 조회된 상품 리스트
   */
  @Operation(summary = "상품 조건 검색", description = "상품 조건 검색", tags = {"상품 API", "조회"})
  @Parameters({
      @Parameter(in =  ParameterIn.QUERY ,name = "pageable", description = "페이지 정보", required = false),
      @Parameter(name = "searchProductOption", description = "상품 검색 조건", required = false,
          content = @Content(schema = @Schema(implementation = SearchProductOptionRequest.class))
      )
  })
  @PostMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> searchProduct(
      @RequestBody SearchProductOptionRequest searchProductOption,
      @RequestParam Pageable pageable) {
    return findProductUseCase.findByOption(searchProductOption, pageable);
  }

  /**
   * 상품 삭제
   *
   * @param id
   * @return 응답
   */
  @Operation(summary = "상품 삭제", description = "상품 삭제", tags = {"상품 API", "삭제"})
  @Parameters({
      @Parameter(name = "id", description = "상품 ID", required = true)
  })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> deleteProduct(
      @PathVariable Long id) {
    return deleteProductUseCase.delete(id);
  }

  /**
   * 상품 판매
   *
   * @param id
   * @param quantity
   * @return 응답
   */
  @Operation(summary = "상품 판매", description = "상품 판매", tags = {"상품 API", "판매"})
  @Parameters({
      @Parameter(in = ParameterIn.PATH,name = "id", description = "상품 ID", required = true),
      @Parameter(in = ParameterIn.PATH,name = "quantity", description = "판매 수량", required = true)
  })
  @PutMapping("/{id}/sell/count/{quantity}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<?> sellProduct(
      @PathVariable Long id,
      @PathVariable Integer quantity) {
    return updateProductUseCase.minusQuantity(id, quantity);
  }
}
