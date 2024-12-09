package com.ecovida.pedido.seguridad;



import com.ecovida.pedido.entidades.Roles;
import com.ecovida.pedido.repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepositorio userRepositorio;

	//carga un user por el nombre o email
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		//Usuario usuario = usuarioRepositorio.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				//.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));

		com.ecovida.pedido.entidades.User user = userRepositorio.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));

		return new User(user.getEmail(), user.getPassword(), mapearRoles1(user.getRole()));
		//return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
	}

	//private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
	//	return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	//}
	private Collection<? extends GrantedAuthority> mapearRoles1(Roles rol) {
		// Inicializamos el conjunto de roles
		Set<Roles> roles = new HashSet<>();

		// Añadimos el rol al conjunto
		roles.add(rol);

		// Mapeamos el conjunto de roles a una lista de SimpleGrantedAuthority
		return roles.stream()
				.map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
				.collect(Collectors.toList());
	}


}
