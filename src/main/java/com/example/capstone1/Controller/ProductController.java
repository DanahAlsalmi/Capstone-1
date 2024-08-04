package com.example.capstone1.Controller;
import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //test Done
    @GetMapping("/products")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    //test Done
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        productService.addProduct(product);
        return ResponseEntity.status(201).body(new ApiResponse("Product added successfully"));
    }

    //test Done
    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@PathVariable int productId, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated = productService.updateProduct(productId, product);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("Product updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
    }

    //test Done
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable int productId) {
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
    }
}
