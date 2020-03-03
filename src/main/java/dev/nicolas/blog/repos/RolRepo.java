package dev.nicolas.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.nicolas.blog.entities.Rol;

import java.util.Optional;

@Repository
public interface RolRepo extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByTipo(String tipo);
}
