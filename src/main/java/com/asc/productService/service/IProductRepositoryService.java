package com.asc.productService.service;

import java.util.List;

import com.asc.productService.enums.Language;
import com.asc.productService.repository.entity.Product;
import com.asc.productService.request.ProductCreateRequest;
import com.asc.productService.request.ProductUpdatedRequest;

public interface IProductRepositoryService {
	
	//Yeni kayıt oluşturma
	Product createProduct(Language language, ProductCreateRequest productCreateRequest); 
	
	//istenilen datayı gösterme
	Product getProduct(Language language, Long productId);  
	
	//tüm kayıtları dönecek  method
	List<Product> getProducts(Language language);
	
	//güncelleme
	Product updateProduct(Language language, Long productId, ProductUpdatedRequest productUpdatedRequest);
	
	//silme
	Product deleteProduct(Language language, Long productId);
	
}
