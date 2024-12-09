package com.ecovida.carrito.repositorio;

import com.ecovida.carrito.entidades.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Método para obtener todos los productos en el carrito de un usuario
    List<CartItem> findByUserId(Long userId);

    // Método para obtener un producto específico en el carrito de un usuario
    CartItem findByUserIdAndProductId(Long userId, Long productId);
}
