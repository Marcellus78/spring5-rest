package com.marcellus.spring5rest.controllers.v1;

import com.marcellus.spring5rest.api.v1.model.CustomerDTO;
import com.marcellus.spring5rest.api.v1.model.CustomerListDTO;
import com.marcellus.spring5rest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers() {

        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,
                                                    @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id,
                                                     @RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
