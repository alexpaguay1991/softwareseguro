package com.ecovida.catalogoproductos.repositorio;



import com.ecovida.catalogoproductos.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
	
}
