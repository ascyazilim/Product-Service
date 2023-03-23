package com.asc.productService.service.impl;

import java.util.List;
import java.util.Objects;

import com.asc.productService.exception.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import com.asc.productService.enums.Language;
import com.asc.productService.exception.enums.FriendlyMessageCodes;
import com.asc.productService.exception.exceptions.ProductNotCreatedException;
import com.asc.productService.repository.ProductRepository;
import com.asc.productService.repository.entity.Product;
import com.asc.productService.request.ProductCreateRequest;
import com.asc.productService.request.ProductUpdatedRequest;
import com.asc.productService.service.IProductRepositoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j   //metotlarda oluşacak hataları terminalden izleyebilmeye yarar. log eklemeye yarar
@Service    // Besinuse logicleri bulunduğu için
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements IProductRepositoryService{
	
	private final ProductRepository productRepository;
	
	@Override
	public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
		log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(),productCreateRequest);
		try {
			Product product = Product.builder()  //builder sadece gerekli olan alanları seçip setler
					.productName(productCreateRequest.getProductName())
					.quantity(productCreateRequest.getQuantity())
					.price(productCreateRequest.getPrice())
					.deleted(false)
					.build();
			Product productResponse = productRepository.save(product);
			log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
			return productResponse;
		} catch (Exception exception) {
			throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "product request: " +productCreateRequest.toString());
		}
		
	}

	@Override
	public Product getProduct(Language language, Long productId) {
		log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
		Product product = productRepository.getByProductIdAndDeletedFalse(productId);
		if(Objects.isNull(product)){
			throw new ProductNotFoundException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Product not found for product id: " +productId);

		}
		log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), product);
		return product;
	}

	@Override
	public List<Product> getProduct(Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Language language, Long productId, ProductUpdatedRequest productUpdatedRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product deleteProduct(Language language, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
