package com.asc.productService.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternalApiResponse<T>{
	
	//Tek tip response yapısı, herturlu yapı icin kullanılak response
	
	private FriendlyMessage friendlyMessage;
	private T payload;  //İstenilen tipte data alabilecek
	private boolean hasError;  //eror var mı
	private List<String> errorMessages;
	private HttpStatus httpStatus;  //Not found, bad request gibi httpstatusleri tutacak
	
}
