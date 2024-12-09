package com.ecovida.pedido.repositorio;



import com.ecovida.pedido.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorio extends JpaRepository<Category, Long> {

}
