package com.analistas.ventas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analistas.ventas.model.domain.Deudor;
import com.analistas.ventas.model.service.IDeudoresService;

@Controller
@RequestMapping("/deudores")
@SessionAttributes({"deudor"})
public class DeudoresController {
	
	@Autowired
	IDeudoresService deudorService;
	
	@GetMapping("/listado")
	public String listar(Model m) {
	
		m.addAttribute("deudor", deudorService.buscarTodo());
		return "deudores/list";
		
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model m) {
		m.addAttribute("deudor", new Deudor());
		
		return "deudores/form";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Deudor deudor, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {
		
		deudorService.guardar(deudor);
		status.isComplete();
		
		return "redirect:/deudores/listado";
	}
	
}
