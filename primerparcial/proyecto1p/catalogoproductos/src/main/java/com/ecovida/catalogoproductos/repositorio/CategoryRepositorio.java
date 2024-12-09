package com.ecovida.catalogoproductos.repositorio;


import com.ecovida.catalogoproductos.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorio extends JpaRepository<Category, Long> {

}
