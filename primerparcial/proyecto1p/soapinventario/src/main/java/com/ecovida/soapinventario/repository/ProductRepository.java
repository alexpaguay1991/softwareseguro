package com.ecovida.soapinventario.repository;

import com.ecovida.soapinventario.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    ProductModel findByName(String name);
}
