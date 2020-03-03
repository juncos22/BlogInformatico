package dev.nicolas.blog.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.nicolas.blog.entities.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);
}
