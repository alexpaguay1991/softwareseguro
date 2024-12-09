package com.ecovida.pedido.controlador;

import com.ecovida.pedido.dto.OrderCreateDTO;
import com.ecovida.pedido.dto.OrderDTO;
import com.ecovida.pedido.entidades.Order;
import com.ecovida.pedido.servicio.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Crear un nuevo pedido
    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        return orderService.createOrder(orderCreateDTO);
    }

    // Ver todos los pedidos (permitido a admin, customer, y support)
    @PreAuthorize("hasAnyRole('admin', 'customer', 'support')")
    @GetMapping("/view")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Ver historial de pedidos de un usuario específico (customer)
    @PreAuthorize("hasAnyRole('admin', 'customer')")
    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // Ver historial de compras de un usuario específico (customer) - Método adicional
    @PreAuthorize("hasAnyRole('admin', 'customer')")
    @GetMapping("/purchase-history/{userId}")
    public List<OrderDTO> getPurchaseHistory(@PathVariable Long userId) {
        return orderService.getPurchaseHistory(userId);
    }



    // Ver detalles de un pedido específico (admin y customer)
    @PreAuthorize("hasAnyRole('admin', 'customer')")
    @GetMapping("/view/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    // Actualizar estado de un pedido (admin)
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/update/{orderId}")
    public OrderDTO updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    // Admin: Eliminar un pedido
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    // Cancelar un pedido (customer)
    @PreAuthorize("hasRole('customer')")
    @PutMapping("/cancel/{orderId}")
    public OrderDTO cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }

    // Actualizar dirección de envío de un pedido (customer)
    @PreAuthorize("hasRole('customer')")
    @PutMapping("/update-shipping/{orderId}")
    public OrderDTO updateShippingAddress(@PathVariable Long orderId, @RequestParam String newAddress) {
        return orderService.updateShippingAddress(orderId, newAddress);
    }

    // Confirmar recepción de un pedido (customer)
    @PreAuthorize("hasRole('customer')")
    @PutMapping("/confirm-receipt/{orderId}")
    public OrderDTO confirmReceipt(@PathVariable Long orderId) {
        return orderService.confirmReceipt(orderId);
    }
}
