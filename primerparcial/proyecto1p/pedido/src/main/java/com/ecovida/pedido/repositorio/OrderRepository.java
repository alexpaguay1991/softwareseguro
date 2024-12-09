package com.ecovida.pedido.repositorio;

import com.ecovida.pedido.entidades.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Método personalizado para obtener las órdenes por userId
    List<Order> findByUserId(Long userId);
}
