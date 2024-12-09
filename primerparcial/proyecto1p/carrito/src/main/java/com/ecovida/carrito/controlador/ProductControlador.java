package com.ecovida.carrito.controlador;



import com.ecovida.carrito.dto.ProductDTO;
import com.ecovida.carrito.servicio.ProductServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProductControlador {

    @Autowired
    private ProductServicio productServicio;

    @GetMapping("/categorias/{categoriaId}/productos")
    public List<ProductDTO> listarProductosPorCategoriaId(@PathVariable(value = "categoriaId") Long categoriaId){
        return productServicio.obtenerProductosPorCategoriaId(categoriaId);
    }
    @GetMapping("/categorias/{categoriaId}/productos/{id}")
    public ResponseEntity<ProductDTO> obtenerProductoPorId(
            @PathVariable(value = "categoriaId") Long categoriaId,
            @PathVariable(value = "id") Long productoId) {
        // Obtener el producto asociado a la categoría
        ProductDTO productDTO = productServicio.obtenerProductoPorId(categoriaId, productoId);
        // Retornar el producto con un estado HTTP 200 (OK)
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @PostMapping("/categorias/{categoriaId}/comentarios")
    public ResponseEntity<ProductDTO> guardarCategoria(@Valid @PathVariable(value = "categoriaId") long categoriaId, @Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productServicio.crearProducto(categoriaId, productDTO),HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/categorias/{categoriaId}/productos/{id}")
    public ResponseEntity<ProductDTO> actualizarProducto(
            @PathVariable(value = "categoriaId") Long categoriaId,
            @PathVariable(value = "id") Long productoId,
            @Valid @RequestBody ProductDTO productDTO) {
        // Llama al servicio para actualizar el producto
        ProductDTO productoActualizado = productServicio.actualizarProducto(categoriaId, productoId, productDTO);

        // Retorna la respuesta con el producto actualizado
        return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/categorias/{categoriaId}/productos/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable(value = "categoriaId") Long categoriaId,
                                                   @PathVariable(value = "id") Long productoId) {
        productServicio.eliminarProducto(categoriaId, productoId);
        return new ResponseEntity<>("Producto eliminado con éxito", HttpStatus.OK);
    }




}
