package com.backend.projekti.tiimityo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
    List<ProductType> findByName(String name);
}
