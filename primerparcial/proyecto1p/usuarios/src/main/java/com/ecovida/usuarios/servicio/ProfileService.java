package com.ecovida.usuarios.servicio;

import com.ecovida.usuarios.entidades.Profile;
import com.ecovida.usuarios.entidades.User;
import com.ecovida.usuarios.repositorio.ProfileRepository;
import com.ecovida.usuarios.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    // Método para crear o actualizar un perfil
    public Profile createOrUpdateProfile(Long userId, Profile profile) {
        // Verificar si el usuario existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        // Verificar si ya existe un perfil asociado al usuario
        Profile existingProfile = profileRepository.findByUserId(userId).orElse(null);

        if (existingProfile != null) {
            // Actualizar perfil existente
            existingProfile.setFirstName(profile.getFirstName());
            existingProfile.setLastName(profile.getLastName());
            existingProfile.setAddress(profile.getAddress());
            existingProfile.setPhoneNumber(profile.getPhoneNumber());
            return profileRepository.save(existingProfile);
        } else {
            // Crear nuevo perfil
            profile.setUser(user);  // Asocia el perfil al usuario existente
            return profileRepository.save(profile);
        }
    }


    // Método para obtener el perfil por id de usuario
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
    // Método para verificar si el usuario autenticado es el propietario del perfil
    public boolean isOwnerOfProfile(Long userId, String username) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found dsds"));
        return Objects.equals(user.getId(), userId);
    }
}
