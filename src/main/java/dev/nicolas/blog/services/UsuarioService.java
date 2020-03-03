package dev.nicolas.blog.services;

import dev.nicolas.blog.entities.Rol;
import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.repos.RolRepo;
import dev.nicolas.blog.repos.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private RolRepo rolRepo;

	private String encodePassword(String rawPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}

	public void registrar(Usuario u) {
		u.setPassword(encodePassword(u.getPassword()));
		Optional<Rol> oRol = rolRepo.findByTipo("USER");
		if (u.getFotoUsuario().equals("")) {
			u.setFotoUsuario("avatar.png");
		}
		if (oRol.isPresent()) {
			Rol user = oRol.get();
			u.setRol(user);
		}
		usuarioRepo.save(u);
	}
	
	public Optional<Usuario> getUsuario(String email) {
		return usuarioRepo.findByEmail(email);
	}
	
	public List<Usuario> listarTodos() {
		return usuarioRepo.findAll();
	}
	
	public Optional<Usuario> getUsuario(Integer id) {
		return usuarioRepo.findById(id);
	}
}
