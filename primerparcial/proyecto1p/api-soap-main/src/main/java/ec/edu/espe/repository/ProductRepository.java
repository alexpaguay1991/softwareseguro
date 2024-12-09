package ec.edu.espe.repository;


import ec.edu.espe.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    ProductModel findByName(String name);
}
