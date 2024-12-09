package com.ecovida.inventario.service.impl;

import com.ecovida.inventario.model.Product;
import com.ecovida.inventario.service.ProductService;
import com.ecovida.inventario.soap.GetProductsRequest;
import com.ecovida.inventario.soap.GetProductsResponse;
import com.ecovida.inventario.soap.GetProductRequest;
import com.ecovida.inventario.soap.GetProductResponse;
import com.ecovida.inventario.soap.PostProductRequest;
import com.ecovida.inventario.soap.PostProductResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private List<Product> productList = new ArrayList<>(); // Aquí deberías conectar a la base de datos

    @Override
    public GetProductsResponse getAllProducts(GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        response.setProducts(productList);  // Aquí recuperarías los productos desde la base de datos
        return response;
    }

    @Override
    public GetProductResponse getProductByName(GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        for (Product product : productList) {
            if (product.getName().equals(request.getName())) {
                response.setProduct(product);
                break;
            }
        }
        return response;
    }

    @Override
    public PostProductResponse addProduct(PostProductRequest request) {
        PostProductResponse response = new PostProductResponse();
        Product newProduct = request.getProduct();
        productList.add(newProduct); // Aquí agregarías el producto a la base de datos
        response.setProduct(newProduct);
        return response;
    }
}
