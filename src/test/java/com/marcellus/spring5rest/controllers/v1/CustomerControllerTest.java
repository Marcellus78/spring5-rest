package com.marcellus.spring5rest.controllers.v1;

import com.marcellus.spring5rest.api.v1.model.CustomerDTO;
import com.marcellus.spring5rest.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRSTNAME = "Joe";
    public static final String LASTTNAME = "Doe";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @Test
    public void listAllCustomers() throws Exception {

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRSTNAME);
        customerDTO1.setLastName(LASTTNAME);
        customerDTO1.setCustomer_url("/api/v1/customers/1");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Jim");
        customerDTO2.setLastName("Weston");
        customerDTO2.setCustomer_url("/api/v1/customers/2");

        //when
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customerDTO1,customerDTO2));

        //then
        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }
    @Test
    public void getCustomerById() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setLastName(LASTTNAME);
        customerDTO.setCustomer_url("/api/v1/customer/1");

        //when
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        //then
        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRSTNAME)));

    }
}