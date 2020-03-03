package dev.nicolas.blog.repos;

import dev.nicolas.blog.entities.Post;
import dev.nicolas.blog.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByFechaCreacion(Date fechaCreacion);
	List<Post> findPostsByUsuario(Usuario u);
	List<Post> findByTituloLike(String titulo);
}
