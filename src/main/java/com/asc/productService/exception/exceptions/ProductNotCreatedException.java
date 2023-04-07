package com.asc.productService.exception.exceptions;

import com.asc.productService.enums.Language;
import com.asc.productService.exception.enums.IFriendlyMessageCode;
import com.asc.productService.exception.utils.FriendlyMessageUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotCreatedException extends RuntimeException{
	private final Language language;
	private final IFriendlyMessageCode friendlyMessageCode;

	public ProductNotCreatedException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
		super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
		this.language = language;
		this.friendlyMessageCode = friendlyMessageCode;
		log.error("[ProductNotCreatedException] -> message: {} developer message: {}", FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode), message);
	}
	
}
