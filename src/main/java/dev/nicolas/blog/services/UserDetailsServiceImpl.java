package dev.nicolas.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.nicolas.blog.entities.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Optional<Usuario> oUsuario = usuarioService.getUsuario(email);
		if (oUsuario.isPresent()) {
			Usuario u = oUsuario.get();
			authorities.add(new SimpleGrantedAuthority(u.getRol().getTipo()));
			return new User(u.getEmail(), u.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("USUARIO NO ENCONTRADO");
	}

}
