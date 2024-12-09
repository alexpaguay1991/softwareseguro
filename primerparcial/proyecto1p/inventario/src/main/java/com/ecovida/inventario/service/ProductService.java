package com.ecovida.inventario.service;

import com.ecovida.inventario.model.Product;
import com.ecovida.inventario.soap.GetProductsRequest;
import com.ecovida.inventario.soap.GetProductsResponse;
import com.ecovida.inventario.soap.GetProductRequest;
import com.ecovida.inventario.soap.GetProductResponse;
import com.ecovida.inventario.soap.PostProductRequest;
import com.ecovida.inventario.soap.PostProductResponse;

import java.util.List;

public interface ProductService {

    GetProductsResponse getAllProducts(GetProductsRequest request);

    GetProductResponse getProductByName(GetProductRequest request);

    PostProductResponse addProduct(PostProductRequest request);
}

