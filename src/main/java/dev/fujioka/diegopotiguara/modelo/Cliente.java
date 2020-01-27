package dev.fujioka.diegopotiguara.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
public class Cliente extends Funcoes{

	//Inicio Atributos
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nomeCliente;
	@NotBlank
	@Column(unique = true)
	private String telefone;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();
	
	// Fim Atributos
	
	
	
	//Inicio Métodos
	
	//Fim Métodos
	
	
	
	//Inicio Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = removeAcentos(nomeCliente).toUpperCase();
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	//Fim Getters and Setters
	
	
}
