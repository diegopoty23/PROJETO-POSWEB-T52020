package dev.fujioka.diegopotiguara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.fujioka.diegopotiguara.modelo.Cliente;
import dev.fujioka.diegopotiguara.repository.ClienteRepository;


@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientedash")
	public String dash(ModelMap model) {
		model.addAttribute("clientes",clienteRepository.findByOrderByNomeClienteAsc());
		return "cliente/dashcliente";
	}
	
	@GetMapping("/cadastrarCliente")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}
	
	@PostMapping("/salvarCliente")
	public String salvar(Cliente cliente, RedirectAttributes var) {
		clienteRepository.save(cliente);
		var.addFlashAttribute("success", "Cadastrado com sucesso.");
		return "redirect:/clientedash";
	}
	
	@GetMapping("/editarCliente/{id}")
	public String preEditar(@PathVariable("id")Long id, ModelMap model) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Cliente Id:" + id));

		model.addAttribute("cliente", cliente);
		return "cliente/cadastro";
	}
	
	
	@PostMapping("/editarCliente")
	public String editar(Cliente cliente, RedirectAttributes var) {
		clienteRepository.saveAndFlush(cliente);
		var.addFlashAttribute("success", "Alterado com sucesso.");
		return "redirect:/clientedash";		
	}
	
	@GetMapping("/excluirCliente/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes var) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid cliente Id:" + id));
		clienteRepository.delete(cliente);
		var.addFlashAttribute("success", "Excluído com sucesso.");
		return "redirect:/clientedash";
	}
	
	@GetMapping("/buscarCliente/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model){
		model.addAttribute("clientes", clienteRepository.findByNomeCliente(nome));
		return "cliente/dashcliente"; 
	}
	
	@GetMapping("/buscarCliente/telefone")
	public String getPorTelefone(@RequestParam("telefone") String telefone, ModelMap model){
		model.addAttribute("clientes", clienteRepository.findByTelefone(telefone));
		return "cliente/dashcliente"; 
	}
	
	

}
