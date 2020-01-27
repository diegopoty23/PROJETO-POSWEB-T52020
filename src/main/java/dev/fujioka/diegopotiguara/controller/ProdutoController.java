package dev.fujioka.diegopotiguara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.fujioka.diegopotiguara.modelo.Produto;
import dev.fujioka.diegopotiguara.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/produtodash")
	public String dash(ModelMap model) {
		model.addAttribute("produtos",produtoRepository.findAll());
		return "produto/dashproduto";
	}
	
	@GetMapping("/cadastrarProduto")
	public String cadastrar(Produto produto) {
		return "produto/cadastro";
	}
	
	@PostMapping("/salvarProduto")
	public String salvar(Produto produto, RedirectAttributes var) {
		produtoRepository.save(produto);
		var.addFlashAttribute("success", "Cadastrado com sucesso.");
		return "redirect:/produtodash";
	}
	
	@GetMapping("/editarProduto/{id}")
	public String preEditar(@PathVariable("id")Long id, ModelMap model) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Produto Id:" + id));

		model.addAttribute("produto", produto);
		return "produto/cadastro";
	}
	
	@PostMapping("/editarProduto")
	public String editar(Produto produto, RedirectAttributes var) {
		produtoRepository.saveAndFlush(produto);
		var.addFlashAttribute("success", "Alterado com sucesso.");
		return "redirect:/produtodash";		
	}
	
	@GetMapping("/excluirProduto/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes var) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid produto Id:" + id));
		produtoRepository.delete(produto);
		var.addFlashAttribute("success", "Excluído com sucesso.");
		return "redirect:/produtodash";
	}
}
