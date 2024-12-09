package com.ecovida.usuarios.controlador;

import com.ecovida.usuarios.dto.UserDTO;
import com.ecovida.usuarios.entidades.Profile;
import com.ecovida.usuarios.entidades.User;
import com.ecovida.usuarios.servicio.ProfileService;
import com.ecovida.usuarios.servicio.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Endpoint para obtener el perfil de un usuario
    @GetMapping("/profile/{username}")
    public UserDTO getUserProfile(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Crear o actualizar perfil (solo el propio usuario puede actualizar su perfil)
    @PreAuthorize("hasAnyRole('admin', 'customer', 'support') and @profileService.isOwnerOfProfile(#userId, authentication.name)")
    @PostMapping("/profile/{userId}")
    public ResponseEntity<Profile> createOrUpdateProfile(@PathVariable Long userId, @RequestBody Profile profile) {
        Profile updatedProfile = profileService.createOrUpdateProfile(userId, profile);
        return ResponseEntity.ok(updatedProfile);
    }

    // Obtener perfil de usuario (solo el propio usuario puede ver su perfil)
    @PreAuthorize("hasAnyRole('admin', 'customer', 'support') and @profileService.isOwnerOfProfile(#userId, authentication.name)")
    @GetMapping("/profile/details/{userId}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long userId) {
        Profile profile = profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }
}
