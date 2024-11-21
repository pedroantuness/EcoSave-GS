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

import br.com.fiap.ecosave.model.Dispositivo;
import br.com.fiap.ecosave.repository.DispositivoRepository;
import jakarta.validation.Valid;

@Controller
public class DispositivoController {

	@Autowired
	private DispositivoRepository repD;
	
	@GetMapping("/dispositivos")
	public ModelAndView retornaListaDispositivos() {

		List<Dispositivo> listaP = repD.findAll();

		ModelAndView mv = new ModelAndView("index_dispositivo");
		mv.addObject("dispositivos", listaP);

		return mv;

	}
	
	@GetMapping("/cadastro_dispositivo")
	public ModelAndView retornaFormCadDispositivo() {

		ModelAndView mv = new ModelAndView("form_cad_dispositivo");
		mv.addObject("dispositivo", new Dispositivo());
		return mv;

	}
	
	@PostMapping("/inserir_dispositivo")
	public ModelAndView cadastroDispositivo(@Valid Dispositivo novo_dispositivo, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_produto");
			mv.addObject("dispositivo", novo_dispositivo);
			return mv;
		} else {
			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setNome(novo_dispositivo.getNome());
			dispositivo.setModelo(novo_dispositivo.getModelo());
			dispositivo.setUsuario(novo_dispositivo.getUsuario());
			dispositivo.setComodo(novo_dispositivo.getComodo());


			repD.save(dispositivo);

			return new ModelAndView("redirect:/produtos");
		}

	}
	
	@GetMapping("/detalhes_dispositivo/{id}")
	public ModelAndView retornaDetalhesDispositivo(@PathVariable Long id) {
	
		Optional<Dispositivo> op = repD.findById(id);
		
		if(op.isPresent()) {
			Dispositivo dispositivo = op.get();
			
			ModelAndView mv = new ModelAndView("detalhes_dispositivo");
			mv.addObject("dispositivo", dispositivo);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/dispositivos");
		}
		
	}
	
	@GetMapping("/atualiza_dispositivo/{id}")
	public ModelAndView retornaAtualizaDispositivo(@PathVariable Long id) {
		
		Optional<Dispositivo> op = repD.findById(id);
		
		if(op.isPresent()) {
			Dispositivo dispositivo = op.get();
			
			ModelAndView mv = new ModelAndView("form_att_dispositivo");
			mv.addObject("dispositivo",dispositivo);
			return mv;
			
		} else {
			return new ModelAndView("redirect:/dispositivos");
		}
		
	}
	
	@PostMapping("/atualizar_dados_dispositivo/{id}")
	public ModelAndView atualizarDispositivo(@PathVariable Long id, @Valid Dispositivo dispositivo, BindingResult bd) {
		
		if(bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_att_dispositivo");
			mv.addObject("dispositivo",dispositivo);
			return mv;
		} else {
			
			Optional<Dispositivo> op = repD.findById(id);
			
			if(op.isPresent()) {
				Dispositivo dispositivo1 = op.get();
				dispositivo1 = dispositivo.toBuilder().setId(dispositivo1.getId()).build();
				
				repD.save(dispositivo1);
				
				return new ModelAndView("redirect:/dispositivos");
			} else {
				return new ModelAndView("redirect:/dispositivos");
			}
			
		}
		
	}
	
	@GetMapping("/remover_dispositivo/{id}")
	public String removerDispositivo(@PathVariable Long id) {
		
		Optional<Dispositivo> op = repD.findById(id);
		
		if(op.isPresent()) {
			repD.deleteById(id);
			return "redirect:/dispositivos";
		} else {
			return "redirect:/dispositivos";
		}
		
	}
	
}
