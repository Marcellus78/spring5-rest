package com.marcellus.spring5rest.api.v1.mapper;

import com.marcellus.spring5rest.api.v1.model.VendorDTO;
import com.marcellus.spring5rest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class VendorMapperTest {

    public static final Long ID = 1L;
    public static final String NAME = "Zetatech";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTOTest() {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertThat(NAME, equalTo(vendorDTO.getName()));
    }
    @Test
    public void vendorDTOToVendor() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertThat(NAME, equalTo(vendor.getName()));
    }
}