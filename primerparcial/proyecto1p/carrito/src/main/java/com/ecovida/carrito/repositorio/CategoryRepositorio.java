package com.ecovida.carrito.repositorio;



import com.ecovida.carrito.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorio extends JpaRepository<Category, Long> {

}
