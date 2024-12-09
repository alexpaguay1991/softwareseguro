package com.ecovida.inventario.repository;

import com.ecovida.inventario.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Puedes agregar consultas personalizadas si es necesario
}
