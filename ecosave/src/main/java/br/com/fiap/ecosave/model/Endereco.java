package br.com.fiap.ecosave.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecosave_endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{endereco.cep.validacao1}")
	@Size(max = 8, message = "{endereco.cep.validacao2}")
	private String cep;
	
	@NotNull(message = "{endereco.numero.validacao1}")
	@Min(value = 1, message = "{endereco.numero.validacao2}")
	@Max(value = 9999, message = "{endereco.numero.validacao3}")
	private Integer numero;

	@Size(max = 255, message = "{endereco.complemento.validacao}")
	private String complemento;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @Valid
    @NotNull(message = "{endereco.cep.validacao}")
    private Usuario usuario;
    
    public class EnderecoBuilder {
		private Long id;
		private String cep;
		private Integer numero;
		private String complemento;
		private Usuario usuario;
		
		public EnderecoBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public EnderecoBuilder setCep(String cep) {
			this.cep = cep;
			return this;
		}
		
		public EnderecoBuilder setNumero(Integer numero) {
			this.numero = numero;
			return this;
		}
		
		public EnderecoBuilder setComplemento(String complemento) {
			this.complemento = complemento;
			return this;
		}
		
		public EnderecoBuilder setUsuario(Usuario usuario) {
			this.usuario = usuario;
			return this;
		}
		
		public EnderecoBuilder setIdUsuario(Long usuario_id) {
			this.usuario.setId(usuario_id);
			return this;
		}
		
		public Endereco build() {
			return new Endereco(this);
		}
		
    }
    
	public Endereco(EnderecoBuilder eb) {
		this.id = eb.id;
		this.cep = eb.cep;
		this.numero = eb.numero;
		this.complemento = eb.complemento;
		this.usuario = eb.usuario;
	}
	
	public EnderecoBuilder toBuilder() {
		return new EnderecoBuilder()
		.setCep(this.cep)
		.setNumero(this.numero)
		.setComplemento(this.complemento)
		.setUsuario(this.usuario);
	}

	public Endereco(Long id, String cep, Integer numero, String complemento, Usuario usuario) {
		super();
		this.id = id;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.usuario = usuario;
	}
	
	public Endereco(Long id, String cep, Integer numero, String complemento) {
		super();
		this.id = id;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public Endereco() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
