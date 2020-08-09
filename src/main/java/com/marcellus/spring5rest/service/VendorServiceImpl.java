package com.marcellus.spring5rest.service;

import com.marcellus.spring5rest.api.v1.mapper.VendorMapper;
import com.marcellus.spring5rest.api.v1.model.VendorDTO;
import com.marcellus.spring5rest.domain.Vendor;
import com.marcellus.spring5rest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.marcellus.spring5rest.controllers.v1.VendorController.BASE_URL;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> getVendorDTO(vendor))
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> getVendorDTO(vendor))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnedVendorDTO.setVendor_url(BASE_URL + "/" + savedVendor.getId());

        return returnedVendorDTO;
    }

    @Override
    public VendorDTO updateVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        returnedVendorDTO.setVendor_url(BASE_URL + "/" + id);

        return returnedVendorDTO;
    }

    @Override
    public VendorDTO patchVendorByDTO(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            returnedVendorDTO.setVendor_url(BASE_URL + "/" + id);
            return returnedVendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO getVendorDTO(Vendor vendor) {
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        vendorDTO.setVendor_url(BASE_URL + "/" + vendor.getId());
        return vendorDTO;
    }
}
