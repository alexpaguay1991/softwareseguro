package com.ecovida.inventario_soap.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Cambiado de Long a Integer

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    // Relación uno a muchos con Product
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    // Constructor vacío
    public Category() {
        super();
    }

    // Constructor con parámetros
    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
