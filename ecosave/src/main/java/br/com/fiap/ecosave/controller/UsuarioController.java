package br.com.fiap.ecosave.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.ecosave.model.Usuario;
import br.com.fiap.ecosave.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repU;
	
	@GetMapping("/usuarios")
	public ModelAndView retornaListaUsuarios() {

		List<Usuario> listaP = repU.findAll();

		ModelAndView mv = new ModelAndView("index_usuario");
		mv.addObject("usuarios", listaP);

		return mv;

	}
	
	@GetMapping("/cadastro_usuario")
	public ModelAndView retornaFormCadUsuario() {

		ModelAndView mv = new ModelAndView("form_cad_usuario");
		mv.addObject("usuario", new Usuario());
		return mv;

	}
	
	@PostMapping("/inserir_usuario")
	public ModelAndView cadastroUsuario(@Valid Usuario novo_usuario, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_usuario");
			mv.addObject("usuario", novo_usuario);
			return mv;
		} else {
			Usuario usuario = new Usuario();
			usuario.setNome(novo_usuario.getNome());
			usuario.setCpf(novo_usuario.getCpf());
			usuario.setEmail(novo_usuario.getEmail());
			usuario.setTelefone(novo_usuario.getTelefone());


			repU.save(usuario);

			return new ModelAndView("redirect:/usuarios");
		}

	}
	
	@GetMapping("/detalhes_usuario/{id}")
	public ModelAndView retornaDetalhesUsuario(@PathVariable Long id) {
	
		Optional<Usuario> op = repU.findById(id);
		
		if(op.isPresent()) {
			Usuario usuario = op.get();
			
			ModelAndView mv = new ModelAndView("detalhes_usuario");
			mv.addObject("usuario", usuario);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/usuarios");
		}
		
	}
	
	@GetMapping("/atualiza_usuario/{id}")
	public ModelAndView retornaAtualizaUsuario(@PathVariable Long id) {
		
		Optional<Usuario> op = repU.findById(id);
		
		if(op.isPresent()) {
			Usuario usuario = op.get();
			
			ModelAndView mv = new ModelAndView("form_att_usuario");
			mv.addObject("usuario",usuario);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/usuarios");
		}
		
	}
	
	@PostMapping("/atualizar_dados_usuario/{id}")
	public ModelAndView atualizarUsuario(@PathVariable Long id, @Valid Usuario usuario, BindingResult bd) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_att_usuario");
			mv.addObject("usuario",usuario);
			return mv;
		} else {
			
			Optional<Usuario> op = repU.findById(id);
			
			if(op.isPresent()) {
				Usuario usuario1 = op.get();
				usuario1 = usuario.toBuilder().setId(usuario1.getId()).build();
				
				repU.save(usuario1);
				
				return new ModelAndView("redirect:/usuarios");
			} else {
				return new ModelAndView("redirect:/usuarios");
			}
			
		}
		
	}
	
	@GetMapping("/remover_usuario/{id}")
	public String removerUsuario(@PathVariable Long id) {
		
		Optional<Usuario> op = repU.findById(id);
		
		if(op.isPresent()) {
			repU.deleteById(id);
			return "redirect:/usuarios";
		} else {
			return "redirect:/usuarios";
		}
		
	}

}
