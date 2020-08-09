package com.marcellus.spring5rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {

    List<VendorDTO> vendors;
}
