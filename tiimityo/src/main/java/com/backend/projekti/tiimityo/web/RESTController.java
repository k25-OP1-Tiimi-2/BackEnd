package com.backend.projekti.tiimityo.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.projekti.tiimityo.domain.Product;
import com.backend.projekti.tiimityo.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/rest")
public class RESTController {

    private final ProductService productService;

    public RESTController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return "Pyynnöt toimii";
    }

    // REST get all products
    @GetMapping("/products")
    public ResponseEntity< List<Product>> getProductsRest() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
    
    // REST create a new product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        
        return ResponseEntity.ok(savedProduct);
    }
    
    // REST update a product
    @PutMapping("product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // REST delete a product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if(deleted) {
            return ResponseEntity.ok("Product ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product with ID " + id);
        }
    }


}
