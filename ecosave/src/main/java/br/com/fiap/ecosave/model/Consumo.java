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
@Table(name = "ecosave_consumo")
public class Consumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 255, message = "{consumo.kwh.validacao}")
	private String kwh;
	
	@Size(max = 255, message = "{consumo.custo.validacao}")
	private String custo;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo")
    @Valid
    @NotNull(message = "{consumo.dispositivo.validacao}")
    private Dispositivo dispositivo;
    
    public class ConsumoBuilder {
    	private Long id;
    	private String kwh;
    	private String custo;
    	private Dispositivo dispositivo;
    	
    	public ConsumoBuilder setId(Long id) {
    		this.id = id;
    		return this;
    	}
    	
    	public ConsumoBuilder setKwh(String kwh) {
    		this.kwh = kwh;
    		return this;
    	}
    	
    	public ConsumoBuilder setCusto(String custo) {
    		this.custo = custo;
    		return this;
    	}
    	
    	public ConsumoBuilder setDispositivo(Dispositivo dispositivo) {
    		this.dispositivo = dispositivo;
    		return this;
    	}
    	
    	public ConsumoBuilder setIdDispositivo(Long dispositivo_id) {
    		this.dispositivo.setId(dispositivo_id);
    		return this;
    	}
    	
    	public Consumo build() {
    		return new Consumo(this);
    	}
    }
    
    public Consumo(ConsumoBuilder cb) {
    	this.id = cb.id;
    	this.kwh = cb.kwh;
    	this.custo = cb.custo;
    	this.dispositivo = cb.dispositivo;
    }
    
    public ConsumoBuilder toBuilder() {
    	return new ConsumoBuilder()
    	.setId(this.id)
    	.setKwh(this.kwh)
    	.setCusto(this.custo)
    	.setDispositivo(this.dispositivo);
    }

	public Consumo(Long id, String kwh, String custo, Dispositivo dispositivo) {
		super();
		this.id = id;
		this.kwh = kwh;
		this.custo = custo;
		this.dispositivo = dispositivo;
	}
	
	public Consumo(Long id, String kwh, String custo) {
		super();
		this.id = id;
		this.kwh = kwh;
		this.custo = custo;
	}
    
    public Consumo() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKwh() {
		return kwh;
	}

	public void setKwh(String kwh) {
		this.kwh = kwh;
	}

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
    
    
    
}
