package br.com.challenge.msproduct.controller;

import br.com.challenge.msproduct.payload.ProductDTO;
import br.com.challenge.msproduct.service.ProductService;
import br.com.challenge.msproduct.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping
    @ResponseBody
    public List<ProductDTO> findAllProductsPaginated(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "linesPerPage", defaultValue = AppConstants.DEFAULT_LINES_PER_PAGE, required = false) int linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = AppConstants.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_DIRECTION, required = false) String direction) {

        return productService.findAllProductsPaginated(page, linesPerPage, orderBy, direction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO prodResponse = productService.updateProduct(id, productDTO);

        return new ResponseEntity<>(prodResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>("Product was successfully deleted", HttpStatus.OK);
    }
}
