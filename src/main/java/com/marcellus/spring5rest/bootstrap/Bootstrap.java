package com.marcellus.spring5rest.bootstrap;

import com.marcellus.spring5rest.domain.Category;
import com.marcellus.spring5rest.domain.Customer;
import com.marcellus.spring5rest.domain.Vendor;
import com.marcellus.spring5rest.repositories.CategoryRepository;
import com.marcellus.spring5rest.repositories.CustomerRepository;
import com.marcellus.spring5rest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded: " + categoryRepository.count() );
    }
    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        System.out.println("Customers Loaded: " + customerRepository.count());
    }
    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Western Tasty Fruits Ltd.");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Exotic Fruits Company");

        Vendor vendor3 = new Vendor();
        vendor3.setId(3L);
        vendor3.setName("Home Fruits");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);

        System.out.println("Vendors Loaded: " + vendorRepository.count());
    }
}
