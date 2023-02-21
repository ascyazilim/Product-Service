package com.asc.productService.controller;

import com.asc.productService.enums.Language;
import com.asc.productService.exception.enums.FriendlyMessageCode;
import com.asc.productService.exception.utils.FriendlyMessageUtils;
import com.asc.productService.repository.entity.Product;
import com.asc.productService.request.ProductCreateRequest;
import com.asc.productService.response.FriendlyMessage;
import com.asc.productService.response.InternalApiResponse;
import com.asc.productService.response.ProductResponse;
import com.asc.productService.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
//classın restful web servisi olarak işlev görür
@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language")Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(),productCreateRequest);
        Product product = productRepositoryService.createProduct(language,productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCode.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCode.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();

    }

    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
