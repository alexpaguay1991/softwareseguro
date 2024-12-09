package com.ecovida.pedido.repositorio;



import com.ecovida.pedido.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositorio extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsernameOrEmail(String username,String email);
    public Optional<User> findByUsername(String username);
    public boolean existsByEmail(String email);
    public boolean existsByUsername(String username);
}
