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

import br.com.fiap.ecosave.model.Ponto;
import br.com.fiap.ecosave.repository.PontoRepository;
import jakarta.validation.Valid;

@Controller
public class PontoController {

	@Autowired
	private PontoRepository repP;
	
	@GetMapping("/pontos")
	public ModelAndView retornaListaPonto() {

		List<Ponto> listaP = repP.findAll();

		ModelAndView mv = new ModelAndView("index_ponto");
		mv.addObject("pontos", listaP);

		return mv;

	}
	
	@GetMapping("/cadastro_ponto")
	public ModelAndView retornaFormCadPonto() {

		ModelAndView mv = new ModelAndView("form_cad_ponto");
		mv.addObject("ponto", new Ponto());
		return mv;

	}
	
	@PostMapping("/inserir_ponto")
	public ModelAndView cadastroPonto(@Valid Ponto novo_ponto, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_ponto");
			mv.addObject("ponto", novo_ponto);
			return mv;
		} else {
			Ponto ponto = new Ponto();
			ponto.setValor(novo_ponto.getValor());
			ponto.setDescricao(novo_ponto.getDescricao());
			ponto.setUsuario(novo_ponto.getUsuario());


			repP.save(ponto);

			return new ModelAndView("redirect:/pontos");
		}

	}
	
	@GetMapping("/detalhes_ponto/{id}")
	public ModelAndView retornaDetalhesPonto(@PathVariable Long id) {
	
		Optional<Ponto> op = repP.findById(id);
		
		if(op.isPresent()) {
			Ponto ponto = op.get();
			
			ModelAndView mv = new ModelAndView("detalhes_ponto");
			mv.addObject("ponto", ponto);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/pontos");
		}
		
	}
	
	@GetMapping("/atualiza_ponto/{id}")
	public ModelAndView retornaAtualizaPonto(@PathVariable Long id) {
		
		Optional<Ponto> op = repP.findById(id);
		
		if(op.isPresent()) {
			Ponto ponto = op.get();
			
			ModelAndView mv = new ModelAndView("form_att_ponto");
			mv.addObject("ponto",ponto);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/pontos");
		}
		
	}
	
	@PostMapping("/atualizar_dados_ponto/{id}")
	public ModelAndView atualizarPonto(@PathVariable Long id, @Valid Ponto ponto, BindingResult bd) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_att_produto");
			mv.addObject("ponto",ponto);
			return mv;
		} else {
			
			Optional<Ponto> op = repP.findById(id);
			
			if(op.isPresent()) {
				Ponto ponto1 = op.get();
				ponto1 = ponto.toBuilder().setId(ponto1.getId()).build();
				
				repP.save(ponto1);
				
				return new ModelAndView("redirect:/pontos");
			} else {
				return new ModelAndView("redirect:/pontos");
			}
			
		}
		
	}
	
	@GetMapping("/remover_ponto/{id}")
	public String removerProduto(@PathVariable Long id) {
		
		Optional<Ponto> op = repP.findById(id);
		
		if(op.isPresent()) {
			repP.deleteById(id);
			return "redirect:/pontos";
		} else {
			return "redirect:/pontos";
		}
		
	}
	
}
