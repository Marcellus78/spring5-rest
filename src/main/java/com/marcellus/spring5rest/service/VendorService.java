package com.marcellus.spring5rest.service;

import com.marcellus.spring5rest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO updateVendorByDTO(Long id, VendorDTO vendorDTO);
    VendorDTO patchVendorByDTO(Long id, VendorDTO vendorDTO);
    void deleteVendorById(Long id);
}
