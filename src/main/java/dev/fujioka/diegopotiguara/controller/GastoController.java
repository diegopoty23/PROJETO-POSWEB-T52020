package dev.fujioka.diegopotiguara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.fujioka.diegopotiguara.modelo.Gasto;
import dev.fujioka.diegopotiguara.repository.GastoRepository;

@Controller
public class GastoController {

	@Autowired
	private GastoRepository gastoRepository;

	@GetMapping("/gastodash")
	public String dash(ModelMap model) {
		model.addAttribute("gastos", gastoRepository.findAll());
		return "gasto/dashgasto";
	}

	@GetMapping("/cadastrarGasto")
	public String cadastrar(Gasto gasto) {
		return "gasto/cadastro";
	}

	@PostMapping("/salvarGasto")
	public String salvar(Gasto gasto, RedirectAttributes var) {
		gastoRepository.save(gasto);
		var.addFlashAttribute("success", "Cadastrado com sucesso.");
		return "redirect:/gastodash";
	}

	@GetMapping("/editarGasto/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Gasto gasto = gastoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Cliente Id:" + id));

		model.addAttribute("gasto", gasto);
		return "gasto/cadastro";
	}

	@PostMapping("/editarGasto")
	public String editar(Gasto gasto, RedirectAttributes var) {
		gastoRepository.saveAndFlush(gasto);
		var.addFlashAttribute("success", "Alterado com sucesso.");
		return "redirect:/gastodash";
	}

	@GetMapping("/excluirGasto/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes var) {
		Gasto gasto = gastoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid cliente Id:" + id));
		gastoRepository.delete(gasto);
		var.addFlashAttribute("success", "Excluído com sucesso.");
		return "redirect:/gastodash";
	}

}
