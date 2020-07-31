package com.marcellus.spring5rest.service;

import com.marcellus.spring5rest.api.v1.mapper.CustomerMapper;
import com.marcellus.spring5rest.api.v1.model.CustomerDTO;
import com.marcellus.spring5rest.domain.Customer;
import com.marcellus.spring5rest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Joe";
    public static final String LASTNAME = "Doe";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getALlCustomersDTO() {

        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDTOList.size());
    }
    @Test
    public void getCustomerDTOById() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(FIRSTNAME, customerDTO.getFirstName());
    }

}