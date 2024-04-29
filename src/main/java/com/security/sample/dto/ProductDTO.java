package com.security.sample.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String description;
    private double price;
    private String imageUrl;
}