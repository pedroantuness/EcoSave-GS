package br.com.fiap.ecosave.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecosave_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{usuario.nome.validacao1}")
	@Size(min = 3, max = 255, message = "{usuario.nome.validacao2}")
	private String nome;
	
    @NotNull(message = "{usuario.cpf.validacao1}")
    @Size(max = 11, message = "{usuario.cpf.validacao2}")
    private String cpf;

	@Email(message = "{usuario.email.validacao}")
    private String email;
	
	@Size(max = 11, message = "{usuario.telefone.validacao}")
	private String telefone;
	
	
	public class UsuarioBuilder{
		private Long id;
		private String nome;
		private String cpf;
		private String email;
		private String telefone;
		
		public UsuarioBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public UsuarioBuilder setNome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public UsuarioBuilder setCpf(String cpf) {
			this.cpf = cpf;
			return this;
		}
		
		public UsuarioBuilder setEmail(String email) {
			this.email = email;
			return this;
		}
		
		public UsuarioBuilder setTelefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public Usuario build() {
			return new Usuario(this);
		}
		
	}
	
	public Usuario(UsuarioBuilder ub) {
		this.id = ub.id;
		this.nome = ub.nome;
		this.cpf = ub.cpf;
		this.email = ub.email;
		this.telefone = ub.telefone;
	}
	
	public UsuarioBuilder toBuilder() {
		return new UsuarioBuilder().
		setNome(this.nome).
		setCpf(this.cpf).
		setEmail(this.email).
		setTelefone(this.telefone);
	}

	public Usuario(Long id, String nome, String cpf, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Usuario() {
		
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
