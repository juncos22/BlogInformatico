package dev.nicolas.blog.controllers;

import dev.nicolas.blog.entities.Post;
import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.services.PostService;
import dev.nicolas.blog.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Optional;

@Controller
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("publicar")
    public String publicar(Post post, HttpServletRequest request) {
        Optional<Usuario> optional = usuarioService.getUsuario(request.getRemoteUser());
        if (optional.isPresent()) {
            Usuario usuario = optional.get();
            post.setUsuario(usuario);
        }
        try {
            postService.crear(post);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/perfil";
    }

    @GetMapping("detalles")
    public String detalles(@RequestParam Integer id, Model model) {
        Optional<Post> optionalPost = postService.obtenerPost(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
        }
        return "posts_usuario";
    }

    @GetMapping("recientes")
    public String recientes(Model model) {
        model.addAttribute("recientes", postService.listarPorFecha());
        return "posts_recientes";
    }

    @GetMapping("buscar")
    public String buscar(@RequestParam String titulo, Model model) {
        model.addAttribute("recientes", postService.buscarPorTitulo(titulo));
        return "posts_recientes";
    }
}
