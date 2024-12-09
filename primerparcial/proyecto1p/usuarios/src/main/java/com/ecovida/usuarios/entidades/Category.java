package com.ecovida.usuarios.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    // Relación uno a muchos con Product
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Product> products;
    private Set<Product> products=new HashSet<Product>();

    // Constructor vacío
    public Category() {
        super();
    }

    // Constructor con parámetros
    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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

    public java.util.Set<Product> getProducts() {
        return products;
    }

    public void setProducts(java.util.Set<Product> products) {
        this.products = products;
    }
    //lombook, permite crear objetos
}

