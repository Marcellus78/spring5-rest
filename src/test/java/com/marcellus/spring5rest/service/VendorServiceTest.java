package com.marcellus.spring5rest.service;

import com.marcellus.spring5rest.api.v1.mapper.VendorMapper;
import com.marcellus.spring5rest.api.v1.model.VendorDTO;
import com.marcellus.spring5rest.domain.Vendor;
import com.marcellus.spring5rest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.marcellus.spring5rest.controllers.v1.VendorController.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class VendorServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Zetatech";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }
    @Test
    public void getAllVendors() {

        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();

        assertEquals(2, vendorDTOList.size());
    }
    @Test
    public void getVendorById() {

        Vendor vendor = getVendor1();

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(NAME, vendorDTO.getName());
    }
    @Test
    public void createNewVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals(BASE_URL + "/" + ID, savedVendorDTO.getVendor_url());
    }
    @Test
    public void updateVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = getVendor1();

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.updateVendorByDTO(ID, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals(BASE_URL + "/" + ID, savedVendorDTO.getVendor_url());
    }

    @Test
    public void patchVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = getVendor1();

        when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.patchVendorByDTO(ID, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals(BASE_URL + "/" + ID,savedVendorDTO.getVendor_url());

    }
    @Test
    public void deleteVendor() {

        vendorService.deleteVendorById(ID);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
    @Test
    public void findVendorByIdNotFound() {

        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vendorService.getVendorById(ID));

        then(vendorRepository).should(times(1)).findById(anyLong());



    }
    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);
        return vendor;
    }

}