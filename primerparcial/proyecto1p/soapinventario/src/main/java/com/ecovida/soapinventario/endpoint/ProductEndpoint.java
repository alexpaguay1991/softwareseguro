package com.ecovida.soapinventario.endpoint;

import com.ecovida.soapinventario.converter.ProductConverter;
import com.ecovida.soapinventario.gen.*;
import com.ecovida.soapinventario.model.ProductModel;
import com.ecovida.soapinventario.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.ecovida.com/soapinventario/gen";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        ProductModel productModel = productRepository.findByName(request.getName());
        response.setProduct(productConverter.convertProductModelToProduct(productModel));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        List<ProductModel> productModels = productRepository.findAll();
        List<Product> products = productConverter.convertProductModelsToProducts(productModels);
        response.getProducts().addAll(products);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postProductRequest")
    @ResponsePayload
    public PostProductResponse postProduct(@RequestPayload PostProductRequest request) {
        PostProductResponse response = new PostProductResponse();
        ProductModel productModel = productConverter.convertProductToProductModel(request.getProduct());
        Product product = productConverter.convertProductModelToProduct(productRepository.save(productModel));
        response.setProduct(product);
        return response;
    }
}
