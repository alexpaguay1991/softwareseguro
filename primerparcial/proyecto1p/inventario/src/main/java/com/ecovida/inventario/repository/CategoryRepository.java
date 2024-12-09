package com.ecovida.inventario.repository;

import com.ecovida.inventario.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}
