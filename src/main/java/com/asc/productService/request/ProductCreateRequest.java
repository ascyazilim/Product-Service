package com.asc.productService.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
	private String productName;
	private Integer quantity;
	private Double price;
}