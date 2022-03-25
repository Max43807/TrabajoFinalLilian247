/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.controller;

import com.analistas.ventas.model.domain.Articulo;
import com.analistas.ventas.model.service.IArticuloService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/articulos")
@SessionAttributes({ "articulo" })
public class ProductoController {

	@Autowired
	IArticuloService articuloService;

	@GetMapping("/listado")
	public String listar(Model m) {

		m.addAttribute("titulo", "Listado de Productos");
		m.addAttribute("articulos", articuloService.buscarTodo());

		return "articulos/list";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("tituloform", "Nuevo Producto");
		model.addAttribute("articulo", new Articulo());

		return "articulos/form";

	}

	@PostMapping("/guardar")
	public String guardar(@Valid Articulo articulo, BindingResult result, Model model, RedirectAttributes redirect,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("error", "debe completar el campo");
			return "articulos/form";
		
		}
		//if (articulo.getId() == 0) {
			//redirect.addFlashAttribute("success", "El producto fue creado correctamente");
		//} else {
			//redirect.addFlashAttribute("warning", "Fue modificado con exito");
		//}
		
		redirect.addFlashAttribute("success", "Producto Guardado con exito");
		model.addAttribute("success", "Creado con exito");
		articuloService.guardar(articulo);
		status.isComplete();

		
		return "redirect:/articulos/listado";
	
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {

		model.addAttribute("tituloform", "Editar Producto");
		model.addAttribute("articulo", articuloService.buscarPorId(id));
		return "articulos/form";
	}

	@GetMapping("/borrar/{id}")
    public String borrar(@PathVariable long id, Model model, RedirectAttributes flash) {
        
        articuloService.borrarPorId(id);
        flash.addFlashAttribute("danger", " El Articulo Fue Eliminado con Exitos... ");
        
        return "redirect:/articulos/listado";
    }

}
