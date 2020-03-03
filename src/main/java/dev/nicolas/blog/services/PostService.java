package dev.nicolas.blog.services;

import dev.nicolas.blog.entities.Post;
import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
	@Autowired
	private PostRepo postRepo;
	
	public void crear(Post p) throws ParseException {
		Date fechaActual = Date.valueOf(LocalDate.now());
		p.setFechaCreacion(fechaActual);
		postRepo.save(p);
	}

	public List<Post> buscarPorTitulo(String titulo) {
		return postRepo.findByTituloLike('%'+titulo+'%');
	}

	public List<Post> listarTodos() {
		return postRepo.findAll();
	}
	
	public List<Post> listarPorFecha() {
		Date fechaActual = Date.valueOf(LocalDate.now());
		return postRepo.findByFechaCreacion(fechaActual);
	}
	
	public List<Post> listarPorUsuario(Usuario u) {
		return postRepo.findPostsByUsuario(u);
	}
	
	public Optional<Post> obtenerPost(Integer id) {
		return postRepo.findById(id);
	}
}
