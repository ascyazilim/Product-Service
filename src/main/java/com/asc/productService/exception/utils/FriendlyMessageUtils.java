package com.asc.productService.exception.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.asc.productService.enums.Language;
import com.asc.productService.exception.enums.IFriendlyMessageCode;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j    //logları daha düzenli okumaya yarar
@UtilityClass  //Class final olarak işaretlenir classdaki metotlar fieldlar static olarak işaretlenir
public class FriendlyMessageUtils {
	
	private static final String RESOURCE_BUNDLE_NAME = "FriendlyMessage";
	private static final String SPECIAL_CHARACTER ="__";
	
	public static String getFriendlyMessage(Language language, IFriendlyMessageCode messageCode) {
		String messageKey = null;
		try {
			Locale locale = new Locale(language.name());
			ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
			messageKey = messageCode.getClass().getSimpleName() + SPECIAL_CHARACTER + messageCode;
			return resourceBundle.getString(messageKey);
		}catch (MissingResourceException missingResourceException) {
			log.error("Friendly message not found for key: {}", messageKey);
			return null;
		}
	}
}
