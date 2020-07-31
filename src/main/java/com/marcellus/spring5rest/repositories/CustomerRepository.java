package com.marcellus.spring5rest.repositories;

import com.marcellus.spring5rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
