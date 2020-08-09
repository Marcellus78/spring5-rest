package com.marcellus.spring5rest.controllers.v1;

import com.marcellus.spring5rest.api.v1.model.VendorDTO;
import com.marcellus.spring5rest.controllers.RestResponseEntityExceptionHandler;
import com.marcellus.spring5rest.service.ResourceNotFoundException;
import com.marcellus.spring5rest.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.marcellus.spring5rest.controllers.v1.VendorController.BASE_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest extends AbstractRestControllerTest {

    public static final String NAME = "Home Fruits";
    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }
    @Test
    public void getAllVendors() throws Exception {

        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName("Name1");

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setName("Name2");

        when(vendorService.getAllVendors()).thenReturn(Arrays.asList(vendorDTO1, vendorDTO2));

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }
    @Test
    public void getVendorById() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendor_url(BASE_URL + "/1");

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));
    }
    @Test
    public void createNewVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        VendorDTO returnedDTO = new VendorDTO();
        returnedDTO.setName(NAME);
        returnedDTO.setVendor_url(BASE_URL + "/1");

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnedDTO);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(BASE_URL + "/1")));
    }
    @Test
    public void updateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        VendorDTO returnedVendorDTO = new VendorDTO();
        returnedVendorDTO.setName(NAME);
        returnedVendorDTO.setVendor_url(BASE_URL + "/1" );

        when(vendorService.updateVendorByDTO(1L,vendorDTO)).thenReturn(returnedVendorDTO);

        mockMvc.perform(put("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(BASE_URL + "/1")));
    }
    @Test
    public void patchVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendor_url(BASE_URL + "/1");

        when(vendorService.patchVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(patch(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
    @Test
    public void deleteVendor() throws Exception {

        mockMvc.perform(delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }
    @Test
    public void vendorNorFoundThrowException() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(BASE_URL + "/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}