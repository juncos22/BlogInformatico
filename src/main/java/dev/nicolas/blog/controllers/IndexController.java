package dev.nicolas.blog.controllers;

import dev.nicolas.blog.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("usuarios", usuarioService.listarTodos());
		return "index";
	}
}
