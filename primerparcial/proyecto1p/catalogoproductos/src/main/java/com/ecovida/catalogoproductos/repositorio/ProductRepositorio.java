package com.ecovida.catalogoproductos.repositorio;


import com.ecovida.catalogoproductos.entidades.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositorio extends JpaRepository<Product, Long> {


    List<Product> findByCategoryId(long categoriaId);


}
