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
@SessionAttributes({"articulo"})
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
	public String guardar(@Valid Articulo articulo, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {
			
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
    
    @PostMapping("/eliminar")
    public String eliminar(@ModelAttribute Articulo articulo, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        articuloService.borrarPorId(articulo.getId());
        return "redirect:/articulos/listado";
    }
    
}
