package dev.fujioka.diegopotiguara.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.fujioka.diegopotiguara.modelo.Pedido;
import dev.fujioka.diegopotiguara.repository.ClienteRepository;
import dev.fujioka.diegopotiguara.repository.PedidoRepository;
import dev.fujioka.diegopotiguara.repository.ProdutoRepository;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	
	@GetMapping("/pedidodash")
	public String dash(ModelMap model) {
		model.addAttribute("pedidos",pedidoRepository.findByOrderByIdDesc());
		return "pedido/dashpedido";
	}
	
	@GetMapping("/cadastrarPedido")
	public String cadastrar(Pedido pedido, ModelMap model) {
		model.addAttribute("produtos", produtoRepository.findAll());
		model.addAttribute("clientes", clienteRepository.findAll());
		return "pedido/cadastro";
	}
	
	@PostMapping("/salvarPedido")
	public String salvar(@Valid Pedido pedido, BindingResult result, RedirectAttributes var) {
		if (result.hasErrors()) {
			return "pedido/cadastro";
		}
		pedido.calcularValorTotal();
		pedidoRepository.save(pedido);
		var.addFlashAttribute("success", "Cadastrado com sucesso.");
		return "redirect:/pedidodash";
	}
	
	@GetMapping("/editarPedido/{id}")
	public String preEditar(@PathVariable("id")Long id, ModelMap model) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Pedido Id:" + id));
		model.addAttribute("produtos", produtoRepository.findAll());
		model.addAttribute("clientes", clienteRepository.findAll());
		model.addAttribute("pedido", pedido);
		return "pedido/editar";
	}
	
	@PostMapping("/editarPedido")
	public String editar(Pedido pedido, RedirectAttributes var) {
		pedido.calcularValorTotal();
		pedidoRepository.saveAndFlush(pedido);
		var.addFlashAttribute("success", "Alterado com sucesso.");
		return "redirect:/pedidodash";		
	}
	
	@GetMapping("/excluirPedido/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes var) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid pedido Id:" + id));
		pedidoRepository.delete(pedido);
		var.addFlashAttribute("success", "Excluído com sucesso.");
		return "redirect:/pedidodash";
	}
	
	
}
