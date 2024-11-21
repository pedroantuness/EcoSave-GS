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

import br.com.fiap.ecosave.model.Comodo;
import br.com.fiap.ecosave.repository.ComodoRepository;
import jakarta.validation.Valid;

@Controller
public class ComodoController {

	@Autowired
	private ComodoRepository repC;
	
	@GetMapping("/comodos")
	public ModelAndView retornaListaComodos() {

		List<Comodo> listaP = repC.findAll();

		ModelAndView mv = new ModelAndView("index_comodo");
		mv.addObject("comodos", listaP);

		return mv;

	}
	
	@GetMapping("/cadastro_comodo")
	public ModelAndView retornaFormCadComodo() {

		ModelAndView mv = new ModelAndView("form_cad_comodo");
		mv.addObject("comodo", new Comodo());
		return mv;

	}
	
	@PostMapping("/inserir_comodo")
	public ModelAndView cadastroComodo(@Valid Comodo novo_comodo, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_comodo");
			mv.addObject("comodo", novo_comodo);
			return mv;
		} else {
			Comodo comodo = new Comodo();
			comodo.setNome(novo_comodo.getNome());


			repC.save(comodo);

			return new ModelAndView("redirect:/comodos");
		}

	}
	
	@GetMapping("/detalhes_comodo/{id}")
	public ModelAndView retornaDetalhesComodo(@PathVariable Long id) {
	
		Optional<Comodo> op = repC.findById(id);
		
		if(op.isPresent()) {
			Comodo comodo = op.get();
			
			ModelAndView mv = new ModelAndView("detalhes_comodo");
			mv.addObject("comodo", comodo);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/comodos");
		}
		
	}
	
	@GetMapping("/atualiza_comodo/{id}")
	public ModelAndView retornaAtualizaComodo(@PathVariable Long id) {
		
		Optional<Comodo> op = repC.findById(id);
		
		if(op.isPresent()) {
			Comodo comodo = op.get();
			
			ModelAndView mv = new ModelAndView("form_att_comodo");
			mv.addObject("comodo",comodo);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/comodos");
		}
		
	}
	
	@PostMapping("/atualizar_dados_comodo/{id}")
	public ModelAndView atualizarComodo(@PathVariable Long id, @Valid Comodo comodo, BindingResult bd) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_att_produto");
			mv.addObject("comodo",comodo);
			return mv;
		} else {
			
			Optional<Comodo> op = repC.findById(id);
			
			if(op.isPresent()) {
				Comodo comodo1 = op.get();
				comodo1 = comodo.toBuilder().setId(comodo1.getId()).build();
				
				repC.save(comodo1);
				
				return new ModelAndView("redirect:/comodos");
			} else {
				return new ModelAndView("redirect:/comodos");
			}
			
		}
		
	}
	
	@GetMapping("/remover_comodo/{id}")
	public String removerComodo(@PathVariable Long id) {
		
		Optional<Comodo> op = repC.findById(id);
		
		if(op.isPresent()) {
			repC.deleteById(id);
			return "redirect:/comodos";
		} else {
			return "redirect:/comodos";
		}
		
	}
	
}
