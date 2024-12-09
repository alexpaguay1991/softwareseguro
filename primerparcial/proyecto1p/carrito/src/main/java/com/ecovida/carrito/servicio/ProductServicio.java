package com.ecovida.carrito.servicio;




import com.ecovida.carrito.dto.ProductDTO;

import java.util.List;
//import com.ecovida.ecovida.dto.ProductRespuesta;

public interface ProductServicio {

    public ProductDTO crearProducto(long categoryId, ProductDTO productDTO);

    public List<ProductDTO> obtenerProductosPorCategoriaId(long categoriaId);

    public ProductDTO actualizarProducto(Long categoriaId, Long productoId, ProductDTO solicitudDeProducto);

    ProductDTO obtenerProductoPorId(Long categoriaId, Long productoId);

    void eliminarProducto(Long categoriaId, Long productoId);

    //public ProductDTO actualizarProducto(ProductDTO productDTO, long id);

    //public ProductDTO obtenerProductoPorId(long id);


   //public void eliminarProducto(long id);
}

