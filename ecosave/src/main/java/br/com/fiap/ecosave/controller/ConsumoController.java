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

import br.com.fiap.ecosave.model.Consumo;
import br.com.fiap.ecosave.repository.ConsumoRepository;
import jakarta.validation.Valid;

@Controller
public class ConsumoController {

	@Autowired
	private ConsumoRepository repC;
	
	@GetMapping("/consumos")
	public ModelAndView retornaListaConsumos() {

		List<Consumo> listaP = repC.findAll();

		ModelAndView mv = new ModelAndView("index_consumo");
		mv.addObject("consumos", listaP);

		return mv;

	}
	
	
	@GetMapping("/cadastro_consumo")
	public ModelAndView retornaFormCadConsumo() {

		ModelAndView mv = new ModelAndView("form_cad_consumo");
		mv.addObject("consumo", new Consumo());
		return mv;

	}
	
	@PostMapping("/inserir_consumo")
	public ModelAndView cadastroConsumo(@Valid Consumo novo_consumo, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_consumo");
			mv.addObject("produto", novo_consumo);
			return mv;
		} else {
			Consumo consumo = new Consumo();
			consumo.setKwh(novo_consumo.getKwh());
			consumo.setCusto(novo_consumo.getCusto());
			consumo.setDispositivo(novo_consumo.getDispositivo());


			repC.save(consumo);

			return new ModelAndView("redirect:/consumos");
		}

	}
	
	@GetMapping("/detalhes_consumo/{id}")
	public ModelAndView retornaDetalhesConsumo(@PathVariable Long id) {
	
		Optional<Consumo> op = repC.findById(id);
		
		if(op.isPresent()) {
			Consumo consumo = op.get();
			
			ModelAndView mv = new ModelAndView("detalhes_consumo");
			mv.addObject("consumo", consumo);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/consumos");
		}
		
	}
	
	@GetMapping("/atualiza_consumo/{id}")
	public ModelAndView retornaAtualizaConsumo(@PathVariable Long id) {
		
		Optional<Consumo> op = repC.findById(id);
		
		if(op.isPresent()) {
			Consumo consumo = op.get();
			
			ModelAndView mv = new ModelAndView("form_att_consumo");
			mv.addObject("consumo",consumo);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/consumos");
		}
		
	}
	
	@PostMapping("/atualizar_dados_consumo/{id}")
	public ModelAndView atualizarConsumo(@PathVariable Long id, @Valid Consumo consumo, BindingResult bd) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_att_consumo");
			mv.addObject("consumo",consumo);
			return mv;
		} else {
			
			Optional<Consumo> op = repC.findById(id);
			
			if(op.isPresent()) {
				Consumo consumo1 = op.get();
				consumo1 = consumo.toBuilder().setId(consumo1.getId()).build();
				
				repC.save(consumo1);
				
				return new ModelAndView("redirect:/consumos");
			} else {
				return new ModelAndView("redirect:/consumos");
			}
			
		}
		
	}
	
	@GetMapping("/remover_consumo/{id}")
	public String removerConsumo(@PathVariable Long id) {
		
		Optional<Consumo> op = repC.findById(id);
		
		if(op.isPresent()) {
			repC.deleteById(id);
			return "redirect:/consumos";
		} else {
			return "redirect:/consumos";
		}
		
	}
	
	
}
