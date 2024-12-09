package com.ecovida.carrito.servicio;

import com.ecovida.carrito.entidades.CartItem;
import com.ecovida.carrito.entidades.Product;
import com.ecovida.carrito.entidades.User;
import com.ecovida.carrito.repositorio.CartItemRepository;

import com.ecovida.carrito.repositorio.ProductRepositorio;
import com.ecovida.carrito.repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServicio {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepositorio userRepository;

    @Autowired
    private ProductRepositorio productRepository;

    // Método para añadir un producto al carrito
    public CartItem agregarProductoAlCarrito(Long userId, Long productId, Integer quantity) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (userOpt.isPresent() && productOpt.isPresent()) {
            User user = userOpt.get();
            Product product = productOpt.get();

            CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

            if (cartItem == null) {
                // Si el producto no está en el carrito, lo añadimos
                cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setAddedAt(LocalDateTime.now());
            } else {
                // Si el producto ya está en el carrito, actualizamos la cantidad
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }

            return cartItemRepository.save(cartItem);
        } else {
            // Si el usuario o el producto no existen, se puede manejar el error aquí
            throw new RuntimeException("Usuario o producto no encontrado");
        }
    }

    // Método para eliminar un producto del carrito
    public void eliminarProductoDelCarrito(Long userId, Long productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }

    // Método para obtener todos los productos en el carrito de un usuario
    public List<CartItem> obtenerCarrito(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
}
