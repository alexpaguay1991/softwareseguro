package com.ecovida.ecovida.servicio;

import com.ecovida.ecovida.dto.CategoryDTO;
import com.ecovida.ecovida.dto.CategoryRespuesta;
import com.ecovida.ecovida.dto.PublicacionRespuesta;

public interface CategoryServicio {

    public CategoryDTO crearCategory(CategoryDTO categoryDTO);

    public CategoryRespuesta obtenerTodasLasCategorias(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);  // Cambiar PublicacionRespuesta a CategoryRespuesta

    public CategoryDTO obtenerCategoryPorId(long id);

    public CategoryDTO actualizarCategory(CategoryDTO categoryDTO, long id);

    public void eliminarCategory(long id);
}
