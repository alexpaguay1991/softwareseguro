package com.ecovida.carrito.controlador;

import com.ecovida.carrito.entidades.CartItem;
import com.ecovida.carrito.servicio.CarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    // Agregar un producto al carrito
    @PreAuthorize("hasAnyRole('admin', 'customer', 'support')")
    @PostMapping("/add")
    public CartItem agregarProductoAlCarrito(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        return carritoServicio.agregarProductoAlCarrito(userId, productId, quantity);
    }

    // Eliminar un producto del carrito
    @PreAuthorize("hasAnyRole('admin', 'customer', 'support')")
    @DeleteMapping("/remove")
    public void eliminarProductoDelCarrito(@RequestParam Long userId, @RequestParam Long productId) {
        carritoServicio.eliminarProductoDelCarrito(userId, productId);
    }

    // Ver los productos en el carrito
    @PreAuthorize("hasAnyRole('admin', 'customer', 'support')")
    @GetMapping("/view")
    public List<CartItem> obtenerCarrito(@RequestParam Long userId) {
        return carritoServicio.obtenerCarrito(userId);
    }
}
