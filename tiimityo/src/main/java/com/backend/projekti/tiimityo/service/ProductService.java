package com.backend.projekti.tiimityo.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.backend.projekti.tiimityo.domain.Product;
import com.backend.projekti.tiimityo.domain.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products. Throw error and message if failed.
    public List<Product> getProducts() {
        try {
            return (List<Product>) productRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all products: " + e.getMessage());
        }
    }

    // Save product. Throw error and message if failed.
    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch(Exception e) {
            throw new RuntimeException("Failed to save product: " + e.getMessage());
        }
    }

    // Update product. Throw error and message if failed.
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Product not found"));
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setProductType(updatedProduct.getProductType());
            existingProduct.setColor(updatedProduct.getColor());
            existingProduct.setSize(updatedProduct.getSize());
            existingProduct.setManufacturer(updatedProduct.getManufacturer());
            productRepository.save(existingProduct);
            return existingProduct;
            
    }

    // Delete product, return true if successful. Throw error and message if failed.
    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product: " + e.getMessage());
        }
    }
}
