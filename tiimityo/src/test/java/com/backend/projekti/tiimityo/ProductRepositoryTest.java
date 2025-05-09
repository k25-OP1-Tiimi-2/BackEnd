package com.backend.projekti.tiimityo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.projekti.tiimityo.domain.ManufacturerRepository;
import com.backend.projekti.tiimityo.domain.Product;
import com.backend.projekti.tiimityo.domain.ProductRepository;
import com.backend.projekti.tiimityo.domain.ProductTypeRepository;

// Tests for local PostgreSQL database

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository prepository;

    @Autowired
    private ManufacturerRepository mrepository;

    @Autowired
    private ProductTypeRepository trepository;

    @Test
    public void findByTitleShouldReturnManufacturer() {
        List<Product> products = prepository.findByTitle("Sadetakki");

        assertThat(products.get(0).getManufacturer().getName()).isEqualTo(mrepository.findByName("Pomppa").get(0).getName());
    }

    @Test
    public void createNewProduct() {
        Product product = new Product("Pehmolelu", 10.0, trepository.findByName("Lelu").get(0), "Punainen", "S", mrepository.findByName("Rukka").get(0), 6);
        prepository.save(product);
        assertThat(product.getId()).isNotNull();
    }
}
