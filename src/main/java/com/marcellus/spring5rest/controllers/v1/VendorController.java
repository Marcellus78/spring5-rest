package com.marcellus.spring5rest.controllers.v1;

import com.marcellus.spring5rest.api.v1.model.VendorDTO;
import com.marcellus.spring5rest.api.v1.model.VendorListDTO;
import com.marcellus.spring5rest.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {

        return new VendorListDTO(vendorService.getAllVendors());
    }
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {

        return vendorService.getVendorById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {

        return vendorService.createNewVendor(vendorDTO);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {

        return vendorService.updateVendorByDTO(id, vendorDTO);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {

        return vendorService.patchVendorByDTO(id,vendorDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {

        vendorService.deleteVendorById(id);
    }
}
