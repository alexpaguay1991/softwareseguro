package com.ecovida.catalogoproductos.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDTO {

    private Long id;

    @NotEmpty(message = "El nombre no debe estar vacío o ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotEmpty(message = "La descripción no debe estar vacía o ser nula")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener un formato válido (hasta 10 enteros y 2 decimales)")
    private BigDecimal price;

    @NotEmpty(message = "La URL de la imagen no debe estar vacía o ser nula")
    @Size(max = 255, message = "La URL de la imagen no debe exceder los 255 caracteres")
    private String imageUrl;

    @Min(value = 0, message = "La cantidad de stock no puede ser negativa")
    private int stockQuantity;

    //@NotNull(message = "El ID de categoría no puede ser nulo")
    private Long categoryId;

    // Getters y Setters

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Constructor sin parámetros
    public ProductDTO() {
    }

    // Constructor con parámetros
    public ProductDTO(Long id, String name, String description, BigDecimal price, String imageUrl, int stockQuantity, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }
}
