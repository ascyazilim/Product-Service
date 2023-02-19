package com.asc.productService.exception.handler;

import java.util.Collection;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.asc.productService.exception.enums.FriendlyMessageCode;
import com.asc.productService.exception.exceptions.ProductNotCreatedException;
import com.asc.productService.exception.utils.FriendlyMessageUtils;
import com.asc.productService.response.FriendlyMessage;
import com.asc.productService.response.InternalApiResponse;

@RestControllerAdvice
//exception handlerları tek bir genel hata componentinde birleştirmeyi sağlar
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ProductNotCreatedException.class)
	public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception){
		return InternalApiResponse.<String>builder()
				.friendlyMessage(FriendlyMessage.builder()
						.title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCode.ERROR))
						.description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
						.build())
				.httpStatus(HttpStatus.BAD_REQUEST)
				.hasError(true)
				.errorMessages(Collections.singletonList(exception.getMessage()))
				.build();
	}
}
