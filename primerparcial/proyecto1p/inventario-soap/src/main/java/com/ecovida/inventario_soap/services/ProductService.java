package com.ecovida.inventario_soap.services;


import com.ecovida.inventario_soap.soap.*;

public interface ProductService {

    GetProductsResponse getAllProducts(GetProductsRequest request);

    GetProductResponse getProductByName(GetProductRequest request);

    PostProductResponse addProduct(PostProductRequest request);
}

