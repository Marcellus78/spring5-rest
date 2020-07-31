package com.marcellus.spring5rest.api.v1.mapper;

import com.marcellus.spring5rest.api.v1.model.CustomerDTO;
import com.marcellus.spring5rest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Joe";
    public static final String LASTTNAME = "Doe";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTTNAME, customerDTO.getLastName());

    }

}