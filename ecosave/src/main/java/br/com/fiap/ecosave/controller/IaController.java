package br.com.fiap.ecosave.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IaController {
	
	@Autowired
	private OpenAiChatClient chatClient;
	
	@GetMapping("form_chat_gpt")
	public String retornaFormChatGPT() {
		return "form_chat_gpt";
	}
	
	@PostMapping("/enviar_chat_gpt")
	public String enviarPerguntaChatGPT(@RequestParam(name = "pergunta") String pergunta, 
			Model model) {
		String resposta = chatClient.call(pergunta);
		model.addAttribute("resposta", resposta );
		return "resposta_chat_gpt";
	}

}
