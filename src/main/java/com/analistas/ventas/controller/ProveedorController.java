package com.analistas.ventas.controller;



import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analistas.ventas.model.domain.Proveedor;
import com.analistas.ventas.model.service.IProveedorService;


@Controller
@RequestMapping("/proveedores")
@SessionAttributes({"proveedor"})
public class ProveedorController {

    @Autowired
    IProveedorService proveeService;

    @GetMapping({"/listado"})
    public String verListado(Model model) {

        model.addAttribute("titulo", "Listado de Proveedores");
        model.addAttribute("proveedores", proveeService.buscarTodo());
        return "proveedores/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Nuevo Proveedor");
        model.addAttribute("tituloForm", "Completar los campos");
        model.addAttribute("proveedor", new Proveedor());

        return "proveedores/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable long id, Model model, RedirectAttributes redirect) {
        
        model.addAttribute("titulo", "Modificar Proveedor");
        model.addAttribute("tituloForm", "Editar los Campos");
        model.addAttribute("proveedor", proveeService.buscarPorId(id));

        return "proveedores/form";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable long id, Model model, RedirectAttributes redirect) {
        proveeService.borrar(id);
        redirect.addFlashAttribute("borrado", "Proveedor Eliminado..");

        return "redirect:/proveedores/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Proveedor p,
            BindingResult r,
            Model model,
            RedirectAttributes redirect,
            SessionStatus status) {

        if (r.hasErrors()) {
            return "proveedores/form";
        }

        if (p.getId() == 0) {
            
            redirect.addFlashAttribute("creado", "Proveedor '" + p.getRazonSocial() + "' creado");
        } else {
            redirect.addFlashAttribute("editado", "Proveedor '" + p.getRazonSocial() + "' modificado");
        }

        proveeService.guardar(p);
        status.setComplete();

        return "redirect:/proveedores/listado";
    }

}
