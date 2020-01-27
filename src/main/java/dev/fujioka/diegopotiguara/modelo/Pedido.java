package dev.fujioka.diegopotiguara.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name = "pedido")
public class Pedido extends Funcoes{

	//Inicio Atributos
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.ABERTO;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPedido; 
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataEntrega;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Produto produto;
	private BigDecimal valorVenda;
	private BigDecimal desconto;
	private BigDecimal valorTotal;
	private String descricaoProduto;
	
		
	// Fim Atributos
	
	
	
	//Inicio Métodos
	
	@SuppressWarnings("unlikely-arg-type")
	public void calcularValorTotal() {
		if(getDesconto() == null || getDesconto().equals("")) {
			setValorTotal(getValorVenda());
			setDesconto(null);
		}else {
			BigDecimal valor = getValorVenda().subtract(getDesconto());
			setValorTotal(valor);
		}
	}
	
	//Fim Métodos
	
	
	
	//Inicio Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = removeAcentos(descricaoProduto).toUpperCase();
	}
	
	
	
	
	
	//Fim Getters and Setters
	
	
}
