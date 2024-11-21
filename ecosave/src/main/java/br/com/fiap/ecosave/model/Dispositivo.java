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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecosave_dispositivo")
public class Dispositivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{dispositivo.nome.validacao1}")
	@Size(max = 255, message = "{dispositivo.nome.validacao2}")
	private String nome;
	
	@Size(max = 255, message = "{dispositivo.modelo.validacao}")
	private String modelo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	@Valid
	@NotNull(message = "{dispositivo.usuario.validacao}")
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_comodo")
	@Valid
	@NotNull(message = "{dispositivo.comodo.validacao}")
	private Comodo comodo;
	
	public class DispositivoBuilder {
		private Long id;
		private String nome;
		private String modelo;
		private Usuario usuario;
		private Comodo comodo;
		
		public DispositivoBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public DispositivoBuilder setNome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public DispositivoBuilder setModelo(String modelo) {
			this.modelo = modelo;
			return this;
		}
		
		public DispositivoBuilder setUsuario(Usuario usuario) {
			this.usuario = usuario;
			return this;
		}
		
		public DispositivoBuilder setComodo(Comodo comodo) {
			this.comodo = comodo;
			return this;
		}
		
		public DispositivoBuilder setIdUsuario(Long usuario_id) {
			this.usuario.setId(usuario_id);
			return this;
		}
		
		public DispositivoBuilder setIdComodo(Long comodo_id) {
			this.comodo.setId(comodo_id);
			return this;
		}
		
		public Dispositivo build() {
			return new Dispositivo(this);
		}

	}
	
	public Dispositivo(DispositivoBuilder db) {
		this.id = db.id;
		this.nome = db.nome;
		this.modelo = db.modelo;
		this.usuario = db.usuario;
		this.comodo = db.comodo;
	}
	
	public DispositivoBuilder toBuilder() {
		return new DispositivoBuilder()
		.setId(this.id)
		.setNome(this.nome)
		.setModelo(this.modelo)
		.setUsuario(this.usuario)
		.setComodo(this.comodo);
	}

	public Dispositivo(Long id, String nome, String modelo, Usuario usuario, Comodo comodo) {
		super();
		this.id = id;
		this.nome = nome;
		this.modelo = modelo;
		this.usuario = usuario;
		this.comodo = comodo;
	}
	
	public Dispositivo(Long id, String nome, String modelo) {
		super();
		this.id = id;
		this.nome = nome;
		this.modelo = modelo;
	}
	
	public Dispositivo() {
		
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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Comodo getComodo() {
		return comodo;
	}

	public void setComodo(Comodo comodo) {
		this.comodo = comodo;
	}
	
	
	
}
