package com.ecovida.usuarios.servicio;


import com.ecovida.usuarios.dto.UserDTO;
import com.ecovida.usuarios.entidades.User;
import com.ecovida.usuarios.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para registrar un nuevo usuario
    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encriptar la contraseña
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    // Método para obtener un usuario por nombre de usuario
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    // Método para convertir User a UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}