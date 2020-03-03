package dev.nicolas.blog;

import dev.nicolas.blog.entities.Categoria;
import dev.nicolas.blog.entities.Rol;
import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.repos.CategoriaRepo;
import dev.nicolas.blog.repos.RolRepo;
import dev.nicolas.blog.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

@SpringBootTest
class BlogInformaticoApplicationTests {
	@Autowired
	private RolRepo rolRepo;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CategoriaRepo categoriaRepo;

	@Test
	void crearRoles() {
		Rol user = new Rol();
		user.setTipo("USER");
		rolRepo.save(user);
	}

	@Test
	void crearUsuarios() {
		Optional<Rol> oRolr = rolRepo.findByTipo("USER");

		if (oRolr.isPresent()) {
			Rol user = oRolr.get();

			Usuario usuario = new Usuario();
			usuario.setNombre("Nicolas");
			usuario.setEmail("nicolas@mail.com");
			usuario.setFechaNacimiento(Date.valueOf("1996-06-14"));
			usuario.setPassword("nicolas23");
			usuario.setRol(user);

			usuarioService.registrar(usuario);
		}else {
			System.out.println("\nNO SE ENCONTRO EL ROL");
		}
	}

	@Test
	void crearCategorias() {
		Categoria c1, c2, c3, c4;
		c1 = new Categoria();
		c1.setTipo("Programacion");

		c2 = new Categoria();
		c2.setTipo("Seguridad Informatica");

		c3 = new Categoria();
		c3.setTipo("Diseño Web");

		c4 = new Categoria();
		c4.setTipo("Bases de Datos");

		categoriaRepo.save(c1);
		categoriaRepo.save(c2);
		categoriaRepo.save(c3);
		categoriaRepo.save(c4);
	}
}
