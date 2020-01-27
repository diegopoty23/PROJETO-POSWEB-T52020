package dev.fujioka.diegopotiguara.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gasto")
public class Gasto extends Funcoes {

	// Inicio Atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String descricaoGasto;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataGasto;
	private BigDecimal valorGasto;

	// Fim Atributos

	// Inicio Métodos

	// Fim Métodos

	// Inicio Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoGasto() {
		return descricaoGasto;
	}

	public void setDescricaoGasto(String descricaoGasto) {
		this.descricaoGasto = descricaoGasto;
	}

	public Date getDataGasto() {
		return dataGasto;
	}

	public void setDataGasto(Date dataGasto) {
		this.dataGasto = dataGasto;
	}

	public BigDecimal getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(BigDecimal valorGasto) {
		this.valorGasto = valorGasto;
	}

	// Fim Getters and Setters

}
