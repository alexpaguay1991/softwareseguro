package com.ecovida.inventario.soap;


import com.ecovida.inventario.model.Product;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class GetProductsResponse {

    private List<Product> products;

    @XmlElement
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
