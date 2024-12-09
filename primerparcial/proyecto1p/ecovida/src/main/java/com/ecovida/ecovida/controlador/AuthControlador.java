package com.ecovida.ecovida.controlador;


import com.ecovida.ecovida.dto.LoginDTO;
import com.ecovida.ecovida.dto.RegistroDTO;
import com.ecovida.ecovida.dto.RegistroUserDTO;
import com.ecovida.ecovida.entidades.Rol;
import com.ecovida.ecovida.entidades.User;
import com.ecovida.ecovida.entidades.Usuario;
import com.ecovida.ecovida.repositorio.RolRepositorio;
import com.ecovida.ecovida.repositorio.UserRepositorio;
import com.ecovida.ecovida.repositorio.UsuarioRepositorio;
import com.ecovida.ecovida.seguridad.JWTAuthResonseDTO;
import com.ecovida.ecovida.seguridad.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	//clase importada
	@Autowired
	private UserRepositorio userRepositorio;
	
	@Autowired
	private RolRepositorio rolRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/iniciarSesion")
	public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthResonseDTO(token));
	}
	@PostMapping("/iniciarSesion1")
	public ResponseEntity<JWTAuthResonseDTO> authenticateUser1(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		//obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);

		return ResponseEntity.ok(new JWTAuthResonseDTO(token));
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
		if(usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		
		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioRepositorio.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
	}
	@PostMapping("/registraruser")
	public ResponseEntity<?> registrarUser(@RequestBody RegistroUserDTO registroUserDTO){
		if(userRepositorio.existsByUsername(registroUserDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
		}

		if(userRepositorio.existsByEmail(registroUserDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
		}


		User user=new User();
		user.setUsername(registroUserDTO.getUsername());
		user.setEmail(registroUserDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registroUserDTO.getPassword()));
		//user.setRole(registroUserDTO.getRole());

		//Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		//usuario.setRoles(Collections.singleton(roles));

		//usuarioRepositorio.save(usuario);
		userRepositorio.save(user);
		return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
	}
}
