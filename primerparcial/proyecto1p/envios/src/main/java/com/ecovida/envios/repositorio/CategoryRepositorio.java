package com.ecovida.envios.repositorio;



import com.ecovida.envios.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorio extends JpaRepository<Category, Long> {

}
