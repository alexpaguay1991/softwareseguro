package com.ecovida.pedido.servicio;


import com.ecovida.pedido.dto.CategoryDTO;
import com.ecovida.pedido.dto.CategoryRespuesta;

public interface CategoryServicio {

    public CategoryDTO crearCategory(CategoryDTO categoryDTO);

    public CategoryRespuesta obtenerTodasLasCategorias(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);  // Cambiar PublicacionRespuesta a CategoryRespuesta

    public CategoryDTO obtenerCategoryPorId(long id);

    public CategoryDTO actualizarCategory(CategoryDTO categoryDTO, long id);

    public void eliminarCategory(long id);
}
