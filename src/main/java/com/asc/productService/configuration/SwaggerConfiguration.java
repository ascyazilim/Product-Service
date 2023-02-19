package com.asc.productService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() {  //Docket documentation plugini implement eder
		return new Docket(DocumentationType.SWAGGER_2)
				.select()  //swaggerdan sunulan endpointleri kontrol etmeyi sağlar
				.apis(RequestHandlerSelectors.basePackage("com.asc"))  // apileri bu paket altında ara
				.paths(PathSelectors.any())  //com.asc altındaki tüm api doc olustur
				.build();
	}

}
