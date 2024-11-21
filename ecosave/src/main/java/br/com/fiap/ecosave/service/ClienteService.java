package br.com.fiap.ecosave.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.ecosave.model.Cliente;
import br.com.fiap.ecosave.repository.ClienteRepository;

@Service
public class ClienteService implements UserDetailsService {

	@Autowired
	private ClienteRepository repC;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Cliente user = repC.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		
		return new User(user.getUsername(), user.getPassword(), 
				user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList()));
	}
	
}
