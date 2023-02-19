package com.asc.productService.request;

import lombok.Data;

@Data
public class ProductUpdatedRequest {
	
	private Long productId;
	private String productName;
	private Integer quanity;
	private Double price;
}
