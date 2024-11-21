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
@Table(name = "ecosave_ponto")
public class Ponto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{ponto.valor.validacao1}")
	@Size(min = 1, max = 4, message = "{ponto.valor.validacao2}")
	private Double valor;
	
	@Size(max = 255, message = "{ponto.descricao.validacao}")
	private String descricao;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @Valid
    @NotNull(message = "{ponto.usuario.validacao}")
    private Usuario usuario;

    public class PontoBuilder {
    	private Long id;
    	private Double valor;
    	private String descricao;
    	private Usuario usuario;
    	
    	public PontoBuilder setId(Long id) {
    		this.id = id;
    		return this;
    	}
    	
    	public PontoBuilder setValor(Double valor) {
    		this.valor = valor;
    		return this;
    	}
    	
    	public PontoBuilder setDescricao(String descricao) {
    		this.descricao = descricao;
    		return this;
    	}
    	
    	public PontoBuilder setUsuario(Usuario usuario) {
    		this.usuario = usuario;
    		return this;
    	}
    	
    	public PontoBuilder setIdUsuario(Long usuario_id) {
    		this.usuario.setId(usuario_id);
    		return this;
    	}
    	
    	public Ponto build() {
    		return new Ponto(this);
    	}
    }
    
    public Ponto(PontoBuilder pb) {
		this.id = pb.id;
		this.valor = pb.valor;
		this.descricao = pb.descricao;
		this.usuario = pb.usuario;
    }
    
    public PontoBuilder toBuilder() {
    	return new PontoBuilder()
    	.setId(this.id)
    	.setValor(this.valor)
    	.setDescricao(this.descricao)
    	.setUsuario(this.usuario);
    }

	public Ponto(Long id, Double valor, String descricao, Usuario usuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
		this.usuario = usuario;
	}
    
	public Ponto(Long id, Double valor, String descricao) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public Ponto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
    
}
