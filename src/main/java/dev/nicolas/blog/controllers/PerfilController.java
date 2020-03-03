package dev.nicolas.blog.controllers;

import dev.nicolas.blog.entities.Post;
import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.repos.CategoriaRepo;
import dev.nicolas.blog.services.PostService;
import dev.nicolas.blog.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("perfil")
public class PerfilController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CategoriaRepo categoriaRepo;
    @Autowired
    private PostService postService;

    @GetMapping
    public String perfil(Model model, HttpServletRequest request) {
        Optional<Usuario> optional = usuarioService.getUsuario(request.getRemoteUser());
        if (optional.isPresent()) {
            Usuario usuario = optional.get();
            model.addAttribute("usuario", usuario);
            model.addAttribute("posts", postService.listarPorUsuario(usuario));
        }
        model.addAttribute("post", new Post());
        model.addAttribute("categorias", categoriaRepo.findAll());

        return "perfil";
    }

}
