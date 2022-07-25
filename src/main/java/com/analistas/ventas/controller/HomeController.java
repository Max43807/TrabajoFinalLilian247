/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.analistas.ventas.model.repository.IUserRepository;
import com.analistas.ventas.model.service.IArticuloService;
import com.analistas.ventas.model.service.ICajaService;
import com.analistas.ventas.model.service.ICompraService;
import com.analistas.ventas.model.service.IProveedorService;
import com.analistas.ventas.model.service.IUserService;
import com.analistas.ventas.model.service.IVentaService;

@Controller
public class HomeController {
	@Autowired
	ICajaService cajaService;

	@Autowired
	IVentaService ventaService;

	@Autowired
	ICompraService compraService;

	@Autowired
	IUserService usuarioService;

	@Autowired
	IArticuloService articuloService;

	@Autowired
	IProveedorService proveedorService;

	@Autowired
	IUserRepository usuarioRepository;

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		model.addAttribute("titulo", "Ventas");
		
		model.addAttribute("masArticulos", articuloService.count(true));
		model.addAttribute("masProveedores", proveedorService.count(true));
		model.addAttribute("masVentas", ventaService.count(true));
		model.addAttribute("masCompras", compraService.count(true));
		model.addAttribute("masUsuarios", usuarioService.count(true));

		return "asd";
	}
}
