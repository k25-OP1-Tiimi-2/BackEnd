package com.backend.projekti.tiimityo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByTitle(@Param("title") String title);

    List<Product> findByManufacturer(Manufacturer manufacturer);

    List<Product> findByType(@Param("type") String type);
}
