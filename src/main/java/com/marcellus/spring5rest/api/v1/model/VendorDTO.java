package com.marcellus.spring5rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    private String name;
    private String vendor_url;
}
