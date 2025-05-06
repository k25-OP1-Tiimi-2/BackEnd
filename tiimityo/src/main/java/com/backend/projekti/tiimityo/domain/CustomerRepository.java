package com.backend.projekti.tiimityo.domain;

import org.springframework.data.repository.CrudRepository;



public interface CustomerRepository extends CrudRepository<Customer, Long>{
        Customer findByUsername(String username);

}
