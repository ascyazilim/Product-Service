package com.asc.productService.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.asc.productService.exception.exceptions.ProductAlreadyDeletedException;
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
	public List<Product> getProducts(Language language) {
		log.debug("[{}][getProducts]", this.getClass().getSimpleName());
		List<Product> products = productRepository.getAllByDeletedFalse();
		if (products.isEmpty()){
			throw new ProductNotFoundException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Products not found");
		}
		log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), products);
		return products;
	}

	@Override
	public Product updateProduct(Language language, Long productId, ProductUpdatedRequest productUpdatedRequest) {
		log.debug("[{}][updateProduct] -> request: {}", this.getClass().getSimpleName(), productUpdatedRequest);
		Product product = getProduct(language, productId);
		product.setProductName(productUpdatedRequest.getProductName());
		product.setQuantity(productUpdatedRequest.getQuanity());
		product.setPrice(productUpdatedRequest.getPrice());
		product.setProductCreatedDate(product.getProductCreatedDate());
		product.setProductUpdatedDate(new Date());
		Product productResponse = productRepository.save(product);
		log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);

		return productResponse;
	}

	@Override
	public Product deleteProduct(Language language, Long productId) {
		log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
		Product product;
		try {
			product = getProduct(language,productId);
			product.setDeleted(true);
			product.setProductUpdatedDate(new Date());
			Product productResponse = productRepository.save(product);
			log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
			return productResponse;
		}catch (ProductNotFoundException productNotFoundException){
			throw new ProductAlreadyDeletedException(language, FriendlyMessageCodes.PRODUCT_ALREADY_DELETED, "Product already deleted product id:" +productId);
		}


	}

}
