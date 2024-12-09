package com.ecovida.inventario.endpoint;

import com.ecovida.inventario.soap.*;
import com.ecovida.inventario.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/demosoap/gen";

    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        return productService.getAllProducts(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        return productService.getProductByName(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postProductRequest")
    @ResponsePayload
    public PostProductResponse addProduct(@RequestPayload PostProductRequest request) {
        return productService.addProduct(request);
    }
}
