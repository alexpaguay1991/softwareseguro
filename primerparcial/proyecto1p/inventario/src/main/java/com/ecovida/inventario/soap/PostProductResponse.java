package com.ecovida.inventario.soap;


import com.ecovida.inventario.model.Product;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostProductResponse {

    private Product product;

    @XmlElement
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
