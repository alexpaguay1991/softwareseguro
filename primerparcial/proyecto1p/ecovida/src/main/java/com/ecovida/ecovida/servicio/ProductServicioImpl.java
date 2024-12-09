package com.ecovida.ecovida.servicio;

import com.ecovida.ecovida.dto.ProductDTO;
import com.ecovida.ecovida.entidades.Category;
import com.ecovida.ecovida.entidades.Product;
import com.ecovida.ecovida.excepciones.EcovidaAppException;
import com.ecovida.ecovida.excepciones.ResourceNotFoundException;
import com.ecovida.ecovida.repositorio.CategoryRepositorio;
import com.ecovida.ecovida.repositorio.ProductRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicioImpl implements ProductServicio {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
   private ProductRepositorio productRepositorio;
    @Autowired
    private CategoryRepositorio categoryRepositorio;
    @Override
    public ProductDTO crearProducto(long categoryId, ProductDTO productDTO) {
        Product product=mapearEntidad(productDTO);
        Category category = categoryRepositorio.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        product.setCategory(category);

        Product nuevoProduct=productRepositorio.save(product);

        return mapearDTO(nuevoProduct);
    }

    @Override
    public List<ProductDTO> obtenerProductosPorCategoriaId(long categoriaId) {
        List<Product> productos = productRepositorio.findByCategoryId(categoriaId);
        return productos.stream().map(product -> mapearDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO actualizarProducto(Long categoriaId, Long productoId, ProductDTO solicitudDeProducto) {
        // Verificar que la categoría exista
        Category categoria = categoryRepositorio.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", categoriaId));

        // Verificar que el producto exista
        Product producto = productRepositorio.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productoId));

        // Validar que el producto pertenezca a la categoría
        if (!producto.getCategory().getId().equals(categoria.getId())) {
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoría especificada");
        }

        // Actualizar los datos del producto con los valores de la solicitud
        producto.setName(solicitudDeProducto.getName());
        producto.setDescription(solicitudDeProducto.getDescription());
        producto.setPrice(solicitudDeProducto.getPrice());
        producto.setImageUrl(solicitudDeProducto.getImageUrl());
        producto.setStockQuantity(solicitudDeProducto.getStockQuantity());

        // Guardar los cambios en la base de datos
        Product productoActualizado = productRepositorio.save(producto);

        // Convertir la entidad actualizada a DTO y devolverla como respuesta
        return mapearDTO(productoActualizado);
    }


    @Override
    public ProductDTO obtenerProductoPorId(Long categoriaId, Long productoId) {
        // Verificar si la categoría existe
        Category categoria = categoryRepositorio.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoriaId));

        // Buscar el producto por su ID
        Product producto = productRepositorio.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productoId));

        // Validar si el producto pertenece a la categoría
        if (!producto.getCategory().getId().equals(categoria.getId())) {
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoría");
        }

        // Mapear el producto a DTO y retornarlo
        return mapearDTO(producto);
    }
    @Override
    public void eliminarProducto(Long categoryId, Long productId) {
        // Verifica si la categoría existe
        Category category = categoryRepositorio.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Verifica si el producto existe
        Product product = productRepositorio.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        // Verifica si el producto pertenece a la categoría
        if (!product.getCategory().getId().equals(category.getId())) {
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoría");
        }

        // Elimina el producto
        productRepositorio.delete(product);
    }




    // Convierte entidad a DTO
    private ProductDTO mapearDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    // Convierte de DTO a Entidad
    private Product mapearEntidad(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return product;
    }

}
