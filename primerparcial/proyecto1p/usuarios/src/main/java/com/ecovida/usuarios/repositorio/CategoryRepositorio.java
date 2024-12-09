package com.ecovida.usuarios.repositorio;



import com.ecovida.usuarios.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorio extends JpaRepository<Category, Long> {

}
