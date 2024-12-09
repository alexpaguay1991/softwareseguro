package com.ecovida.ecovida.servicio;

import com.ecovida.ecovida.dto.CategoryDTO;
import com.ecovida.ecovida.dto.CategoryRespuesta;
import com.ecovida.ecovida.entidades.Category;
import com.ecovida.ecovida.excepciones.ResourceNotFoundException;
import com.ecovida.ecovida.repositorio.CategoryRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServicioImpl implements CategoryServicio {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepositorio categoryRepositorio;

    @Override
    public CategoryDTO crearCategory(CategoryDTO categoryDTO) {
        Category category = mapearEntidad(categoryDTO);

        Category nuevaCategory = categoryRepositorio.save(category);

        CategoryDTO categoryRespuesta = mapearDTO(nuevaCategory);
        return categoryRespuesta;
    }

    @Override
    public CategoryRespuesta obtenerTodasLasCategorias(int numeroDePagina, int medidaDePagina, String ordenarPor,
                                                       String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Category> categorias = categoryRepositorio.findAll(pageable);

        List<Category> listaDeCategorias = categorias.getContent();
        List<CategoryDTO> contenido = listaDeCategorias.stream().map(category -> mapearDTO(category))
                .collect(Collectors.toList());

        CategoryRespuesta categoryRespuesta = new CategoryRespuesta();
        categoryRespuesta.setContenido(contenido);
        categoryRespuesta.setNumeroPagina(categorias.getNumber());
        categoryRespuesta.setMedidaPagina(categorias.getSize());
        categoryRespuesta.setTotalElementos(categorias.getTotalElements());
        categoryRespuesta.setTotalPaginas(categorias.getTotalPages());
        categoryRespuesta.setUltima(categorias.isLast());

        return categoryRespuesta;
    }

    @Override
    public CategoryDTO obtenerCategoryPorId(long id) {
        Category category = categoryRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapearDTO(category);
    }

    @Override
    public CategoryDTO actualizarCategory(CategoryDTO categoryDTO, long id) {
        Category category = categoryRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category categoryActualizada = categoryRepositorio.save(category);
        return mapearDTO(categoryActualizada);
    }

    @Override
    public void eliminarCategory(long id) {
        Category category = categoryRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepositorio.delete(category);
    }

    // Convierte entidad a DTO
    private CategoryDTO mapearDTO(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    // Convierte de DTO a Entidad
    private Category mapearEntidad(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }
}
