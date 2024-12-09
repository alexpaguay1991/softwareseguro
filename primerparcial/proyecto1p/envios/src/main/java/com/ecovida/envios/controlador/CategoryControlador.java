package com.ecovida.envios.controlador;



import com.ecovida.envios.dto.CategoryDTO;
import com.ecovida.envios.dto.CategoryRespuesta;
import com.ecovida.envios.servicio.CategoryServicio;
import com.ecovida.envios.utilerias.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoryControlador {

    @Autowired
    private CategoryServicio categoryServicio;

    // Listar todas las categorías
    @GetMapping
    public CategoryRespuesta listarCategorias(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return categoryServicio.obtenerTodasLasCategorias(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    //@PreAuthorize("admin","user")
    public ResponseEntity<CategoryDTO> obtenerCategoriaPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(categoryServicio.obtenerCategoryPorId(id));
    }

    // Crear una nueva categoría
    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public ResponseEntity<CategoryDTO> guardarCategoria(@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryServicio.crearCategory(categoryDTO), HttpStatus.CREATED);
    }

    // Actualizar una categoría existente
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> actualizarCategoria(@Valid @RequestBody CategoryDTO categoryDTO,
                                                           @PathVariable(name = "id") long id) {
        CategoryDTO categoryRespuesta = categoryServicio.actualizarCategory(categoryDTO, id);
        return new ResponseEntity<>(categoryRespuesta, HttpStatus.OK);
    }

    // Eliminar una categoría
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable(name = "id") long id) {
        categoryServicio.eliminarCategory(id);
        return new ResponseEntity<>("Categoría eliminada con éxito", HttpStatus.OK);
    }
}
