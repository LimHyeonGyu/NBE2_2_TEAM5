package edu.example.dev_2_cc.api_controller;

import edu.example.dev_2_cc.dto.product.ProductRequestDTO;
import edu.example.dev_2_cc.dto.product.ProductResponseDTO;
import edu.example.dev_2_cc.dto.product.ProductUpdateDTO;
import edu.example.dev_2_cc.exception.ProductException;
import edu.example.dev_2_cc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/cc/admin")
public class AdminController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.create(productRequestDTO));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateDTO productUpdateDTO) {
        if(!productId.equals(productUpdateDTO.getProductId())) {
            throw ProductException.NOT_FOUND.get();
        }
        return ResponseEntity.ok(productService.update(productUpdateDTO));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok(Map.of("message", "Product deleted"));
    }

}
