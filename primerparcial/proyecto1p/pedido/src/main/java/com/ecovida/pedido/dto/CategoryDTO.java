package com.ecovida.pedido.dto;




import com.ecovida.pedido.entidades.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class CategoryDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "El nombre de la categoría debería tener al menos 2 caracteres")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "La descripción de la categoría debería tener al menos 10 caracteres")
    private String description;

    private Set<Product> products;
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDTO() {
        super();
    }
}
