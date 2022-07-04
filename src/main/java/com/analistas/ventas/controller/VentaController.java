/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.controller;

import com.analistas.ventas.model.domain.Articulo;
import com.analistas.ventas.model.domain.LineaVenta;
import com.analistas.ventas.model.domain.Venta;
import com.analistas.ventas.model.service.IArticuloService;
import com.analistas.ventas.model.service.IVentaService;
import groovy.transform.AutoClone;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ventas")
@SessionAttributes({ "venta" })
public class VentaController {

	@Autowired
	IArticuloService productoService;

	@Autowired
	IVentaService ventaService;

	@GetMapping("/nueva")
	public String nuevaVenta(Model model) {

		Venta venta = new Venta();
		venta.setFechaHora(LocalDateTime.now());

		model.addAttribute("titulo", "Nueva Venta");
		model.addAttribute("venta", venta);

		return "ventas/form";
	}

	@GetMapping({ "/", "/home" })
	public String home(Model m) {
		m.addAttribute("titulo", "Ventas");

		return "redirect:/ventas/nueva";
	}

	@PostMapping("/guardar")
	public String guardarVenta(@Valid Venta venta, BindingResult result,
			@RequestParam(name = "item_id[]", required = true) List<String> itemId,
			@RequestParam(name = "precio_actual[]", required = true) List<String> precioActual,
			@RequestParam(name = "cantidad[]", required = true) List<String> cantidad, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			flash.addAttribute("error", "Corrija los errores");
			return "ventas/form";
		}

		if (itemId == null || itemId.size() == 1) {
			model.addAttribute("titulo", "Añadir productos a la venta");
			model.addAttribute("error", "La venta está vacía");
			return "ventas/form";
		}
		

		LineaVenta linea = new LineaVenta();
		Articulo articulo = new Articulo();

		for (int i = 1; i < itemId.size(); i++) {
			linea = new LineaVenta();
			Long id = Long.parseLong(itemId.get(i));
			articulo = productoService.buscarPorId(id);

			linea.setArticulo(articulo);
			linea.setCantidad(Integer.parseInt(cantidad.get(i)));
			articulo.restarStock(linea.getCantidad());

			venta.addLinea(linea);
		}
		
		if (articulo.getStock() < 0) {
			model.addAttribute("sinStock", "No tiene stock");
			return "ventas/form";
		}

		ventaService.guardar(venta);
		productoService.guardar(articulo);
		status.setComplete();
		flash.addFlashAttribute("success", "Venta Registrada");

		return "redirect:/ventas/nueva";
	}

	@ModelAttribute("productos")
	public List<Articulo> getProductos() {
		return productoService.buscarTodo();
	}

	@GetMapping({ "/listado" })
	public String verListado(Model model) {
		model.addAttribute("ventas", ventaService.listarTodo());

		return "ventas/list";
	}
}
