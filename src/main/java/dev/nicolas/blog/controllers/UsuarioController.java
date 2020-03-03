package dev.nicolas.blog.controllers;

import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String buscar(@RequestParam Integer id, Model model) {
        Optional<Usuario> optional = usuarioService.getUsuario(id);
        optional.ifPresent(usuario -> model.addAttribute("usuario", usuario));
        return "usuario";
    }
}
