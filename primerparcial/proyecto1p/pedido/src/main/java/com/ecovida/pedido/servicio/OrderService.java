package com.ecovida.pedido.servicio;

import com.ecovida.pedido.dto.OrderCreateDTO;
import com.ecovida.pedido.dto.OrderDTO;
import com.ecovida.pedido.entidades.Order;
import com.ecovida.pedido.entidades.OrderStatus;
import com.ecovida.pedido.entidades.PaymentMethod;
import com.ecovida.pedido.entidades.User;
import com.ecovida.pedido.repositorio.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService; // Se debe tener acceso al servicio de usuario

    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO) {
        // Implementación de creación de pedido
        User user = userService.findById(orderCreateDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotal(orderCreateDTO.getTotal());
        order.setStatus(OrderStatus.valueOf(orderCreateDTO.getStatus().toString().toLowerCase()));
        order.setShippingAddress(orderCreateDTO.getShippingAddress());
        order.setPaymentMethod(PaymentMethod.valueOf(orderCreateDTO.getPaymentMethod().toString().toLowerCase()));
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO updateOrderStatus(Long orderId, String newStatus) {
        OrderStatus statusEnum = OrderStatus.valueOf(newStatus);
        System.out.println(statusEnum);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(statusEnum);
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    public OrderDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.cancelled); // O el estado equivalente
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    public OrderDTO updateShippingAddress(Long orderId, String newAddress) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setShippingAddress(newAddress);
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    public OrderDTO confirmReceipt(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.completed); // O el estado equivalente
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }
    // Método para obtener el historial de compras de un usuario específico
    public List<OrderDTO> getPurchaseHistory(Long userId) {
        // Obtener todos los pedidos del usuario
        List<Order> orders = orderRepository.findByUserId(userId);

        // Convertir las entidades Order a OrderDTO y devolver
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        // Asignar el ID del pedido
        orderDTO.setId(order.getId());

        // Obtener el nombre de usuario (asumiendo que la entidad Order tiene un objeto User asociado)
        orderDTO.setUsername(order.getUser().getUsername());  // 'order.getUser()' es la relación con la entidad User

        // Asignar el total del pedido
        orderDTO.setTotal(order.getTotal());

        // Asignar el estado del pedido
        orderDTO.setStatus(order.getStatus());

        // Asignar la dirección de envío
        orderDTO.setShippingAddress(order.getShippingAddress());

        // Asignar el método de pago
        orderDTO.setPaymentMethod(order.getPaymentMethod());

        return orderDTO;
    }

}
