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

import br.com.fiap.ecosave.model.Endereco;
import br.com.fiap.ecosave.repository.EnderecoRepository;
import jakarta.validation.Valid;



@Controller
public class EnderecoController {

	@Autowired
	private EnderecoRepository repE;
	
	@GetMapping("/enderecos")
	public ModelAndView retornaListaEnderecos() {

		List<Endereco> listaE = repE.findAll();

		ModelAndView mv = new ModelAndView("index_endereco");
		mv.addObject("enderecos", listaE);

		return mv;

	}
	
	@GetMapping("/cadastro_endereco")
	public ModelAndView retornaFormCadEndereco() {

		ModelAndView mv = new ModelAndView("form_cad_endereco");
		mv.addObject("endereco", new Endereco());
		return mv;

	}
	
	@PostMapping("/inserir_endereco")
	public ModelAndView cadastroEndereco(@Valid Endereco novo_endereco, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_endereco");
			mv.addObject("endereco", novo_endereco);
			return mv;
		} else {
			Endereco endereco = new Endereco();
			endereco.setCep(novo_endereco.getCep());
			endereco.setNumero(novo_endereco.getNumero());
			endereco.setComplemento(novo_endereco.getComplemento());
			endereco.setUsuario(novo_endereco.getUsuario());

			repE.save(endereco);

			return new ModelAndView("redirect:/enderecos");
		}

	}
	
	@GetMapping("/detalhes_endereco/{id}")
	public ModelAndView retornaDetalhesEndereco(@PathVariable Long id) {
	
		Optional<Endereco> op = repE.findById(id);
		
		if(op.isPresent()) {
			Endereco endereco = op.get();
			
			ModelAndView mv = new ModelAndView("detalhes_endereco");
			mv.addObject("endereco", endereco);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/enderecos");
		}
		
	}
	
	@GetMapping("/atualiza_endereco/{id}")
	public ModelAndView retornaAtualizaEndereco(@PathVariable Long id) {
		
		Optional<Endereco> op = repE.findById(id);
		
		if(op.isPresent()) {
			Endereco endereco = op.get();
			
			ModelAndView mv = new ModelAndView("form_att_endereco");
			mv.addObject("endereco", endereco);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/enderecos");
		}
		
	}
	
	@PostMapping("/atualiza_dados_endereco/{id}")
	public ModelAndView atualizarEndereco(@PathVariable Long id, @Valid Endereco endereco, BindingResult bd) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_att_endereco");
			mv.addObject("endereco",endereco);
			return mv;
		} else {
			
			Optional<Endereco> op = repE.findById(id);
			
			if(op.isPresent()) {
				Endereco endereco1 = op.get();
				endereco1 = endereco.toBuilder().setId(endereco1.getId()).build();
				
				repE.save(endereco1);
				
				return new ModelAndView("redirect:/enderecos");
			} else {
				return new ModelAndView("redirect:/enderecos");
			}
			
		}
		
	}
	
	@GetMapping("/remover_endereco/{id}")
	public String removerEndereco(@PathVariable Long id) {
		
		Optional<Endereco> op = repE.findById(id);
		
		if(op.isPresent()) {
			repE.deleteById(id);
			return "redirect:/enderecos";
		} else {
			return "redirect:/enderecos";
		}
		
	}
	
}
