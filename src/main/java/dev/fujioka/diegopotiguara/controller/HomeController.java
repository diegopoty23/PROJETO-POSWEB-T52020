package dev.fujioka.diegopotiguara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.fujioka.diegopotiguara.modelo.Pedido;
import dev.fujioka.diegopotiguara.modelo.StatusPedido;
import dev.fujioka.diegopotiguara.repository.ClienteRepository;
import dev.fujioka.diegopotiguara.repository.GastoRepository;
import dev.fujioka.diegopotiguara.repository.PedidoRepository;
import dev.fujioka.diegopotiguara.repository.ProdutoRepository;

@Controller
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private GastoRepository gastoRepository;

	@GetMapping("/")
	public String home(ModelMap model) {
		model.addAttribute("valorTotalEmCaixa", pegarValorTotal() - pegarValorTotalGasto());
		model.addAttribute("valorGasto", pegarValorTotalGasto());
		model.addAttribute("valorVenda", pegarValorTotal());
		model.addAttribute("qtdDeVendas", pegarTotalDeVendas());
		model.addAttribute("qtdDeClientes", pegarTotalDeClientes());
		model.addAttribute("qtdDeProdutos", pegarTotalDeProdutos());
		model.addAttribute("pedidosEmAberto", pedidoRepository.pedidosEmAberto());
		return "home";
	}
	
	public Double pegarValorTotal() {
		Double valorTotal = pedidoRepository.calcularValorTotalEmCaixa();
		if(valorTotal == null) {
			return 0.0;
		}
		return valorTotal;
	}
	
	public Double pegarValorTotalGasto() {
		Double valorTotalGasto = gastoRepository.calcularValorTotalGasto();
		if(valorTotalGasto == null) {
			return 0.0;
		}
		return valorTotalGasto;
	}
	
	public Integer pegarTotalDeVendas() {
		Integer vendas = pedidoRepository.quantidadeDeVendasEmGeral();
		if(vendas == null) {
			return 0;
		}
		return vendas;
	}
	
	public Integer pegarTotalDeClientes() {
		Integer clientes = clienteRepository.quantidadeDeClientesEmGeral();
		if(clientes == null) {
			return 0;
		}
		return clientes;
	}
	
	public Integer pegarTotalDeProdutos() {
		Integer produtos = produtoRepository.quantidadeDeProdutosEmGeral();
		if(produtos == null) {
			return 0;
		}
		return produtos;
	}
	
	@GetMapping("/statusEmAndamento/{id}")
	public String alterarStatusParaEmAndamento(@PathVariable("id") Long id, RedirectAttributes var) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid pedido Id:" + id));
		pedido.setStatus(StatusPedido.EM_ANDAMENTO);
		pedidoRepository.saveAndFlush(pedido);
		var.addFlashAttribute("success", "Pedido em andamento.");
		return "redirect:/";
	}
	
	@GetMapping("/statusFinalizado/{id}")
	public String alterarStatusParaFinalizado(@PathVariable("id") Long id, RedirectAttributes var) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid pedido Id:" + id));
		pedido.setStatus(StatusPedido.FINALIZADO);
		pedidoRepository.saveAndFlush(pedido);
		var.addFlashAttribute("success", "Pedido Finalizado com sucesso.");
		return "redirect:/";
	}
}
