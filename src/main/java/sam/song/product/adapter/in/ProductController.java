package sam.song.product.adapter.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sam.song.product.adapter.in.request.CreateProductRequest;
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


  @PostMapping("")
  public CommonResponse<?> createProduct(
      @Validated @RequestBody CreateProductRequest createProductDto) {
    return createProductUseCase.create(createProductDto);

  }

  @PutMapping("/{id}")
  public CommonResponse updateProduct(
      @PathVariable Long id,
      @Validated @RequestBody UpdateProductRequest updateProductDto) {
    return updateProductUseCase.update(id, updateProductDto);
  }

  @GetMapping("")
  public CommonResponse<?> findProduct() {
    return findProductUseCase.findAll();
  }

  @GetMapping("/{id}")
  public CommonResponse<?> findProductById(
      @PathVariable Long id) {
    return findProductUseCase.find(id);
  }

}
