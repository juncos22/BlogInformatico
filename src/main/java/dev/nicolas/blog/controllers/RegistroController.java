package dev.nicolas.blog.controllers;

import dev.nicolas.blog.entities.Usuario;
import dev.nicolas.blog.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class RegistroController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("registrarse")
    public String registrarse(Usuario usuario, @RequestParam("foto") MultipartFile file,
                              HttpServletRequest request, Model model) {
        String confirmar = request.getParameter("confirmar");
        if (confirmar.equals(usuario.getPassword())) {
            if (!file.isEmpty()) {
                try {
                    Path ruta = Paths.get(".//src//main//resources//static//archivos//"+file.getOriginalFilename());
                    Files.write(ruta, file.getBytes());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                usuario.setFotoUsuario(file.getOriginalFilename());
            }
            usuarioService.registrar(usuario);
        }else {
            model.addAttribute("mensaje", "Las Contraseñas No Coinciden");
            return registro(model);
        }
        return "redirect:/";
    }
}
