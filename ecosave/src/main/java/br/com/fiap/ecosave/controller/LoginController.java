package br.com.fiap.ecosave.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.ecosave.model.Cliente;
import br.com.fiap.ecosave.model.Role;
import br.com.fiap.ecosave.repository.ClienteRepository;
import br.com.fiap.ecosave.repository.RoleRepository;

@Controller
public class LoginController {

	@Autowired
	private ClienteRepository repC;
	
	@Autowired
	private RoleRepository repR;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/index")
    public String retornaPagina() {
        return "index";
    }
	
	@GetMapping("/acesso_negado")
	public String acessoNegado() {
		return "acesso_negado";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/novo_cliente")
	public ModelAndView retornaCadCliente() {
		ModelAndView mv = new ModelAndView("novo_cliente");
		mv.addObject("cliente", new Cliente());
		mv.addObject("roles", repR.findAll());
		return mv;	
	}
	
	@PostMapping("/inserir_cliente")
	public ModelAndView cadastrarCliente(Cliente cliente, BindingResult bd, 
			@RequestParam(name = "id_role") Long id_role ) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("novo_cliente");
			mv.addObject("cliente",cliente);
			mv.addObject("roles",repR.findAll());
			return mv;
		} else {
			
			cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
			
			Set<Role> lista = new HashSet<>();
			
			
			if(id_role != null) {
				
				lista.add(repR.findById(id_role).orElse(null));
				
			}
			
			cliente.setRoles(lista);
			
			repC.save(cliente);
			
			return new ModelAndView("redirect:/index");
			
		}
		
		
	}
	
}
