package com.marcellus.spring5rest.repositories;

import com.marcellus.spring5rest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
