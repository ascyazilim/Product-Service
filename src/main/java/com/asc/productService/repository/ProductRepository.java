package com.asc.productService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asc.productService.repository.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product getByProductIdAndDeletedFalse(Long productId);  //verilen id isdeletedfalse alanı false ise getir
	
	List<Product> getAllByDeletedFalse();   //isdeleted alanı false olan dataları verir

}
