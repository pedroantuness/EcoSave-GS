package br.com.fiap.ecosave.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecosave_comodo")
public class Comodo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{comodo.nome.validacao1}")
	@Size(max = 255, message = "{comodo.nome.validacao2}")
	private String nome;
	
	public class ComodoBuilder{
		private Long id;
		private String nome;
		
		public ComodoBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public ComodoBuilder setNome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Comodo build() {
			return new Comodo(this);
		}
	}
	
	public Comodo(ComodoBuilder cb) {
		this.id = cb.id;
		this.nome = cb.nome;
	}
	
	public ComodoBuilder toBuilder() {
		return new ComodoBuilder()
		.setNome(this.nome);
	}

	public Comodo(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Comodo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
