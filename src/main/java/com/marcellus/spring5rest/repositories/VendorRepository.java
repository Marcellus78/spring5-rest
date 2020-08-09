package com.marcellus.spring5rest.repositories;

import com.marcellus.spring5rest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
