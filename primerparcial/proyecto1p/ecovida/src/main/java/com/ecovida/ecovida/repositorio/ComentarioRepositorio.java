package com.ecovida.ecovida.repositorio;


import com.ecovida.ecovida.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{

	public List<Comentario> findByPublicacionId(long publicacionId); 
	
}
