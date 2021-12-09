package com.analistas.ventas.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analistas.ventas.model.domain.Articulo;
import com.analistas.ventas.model.domain.Compra;
import com.analistas.ventas.model.domain.LineaCompra;
import com.analistas.ventas.model.service.IArticuloService;
import com.analistas.ventas.model.service.ICompraService;
import com.analistas.ventas.model.service.IProveedorService;

@Controller
@RequestMapping("/compras") // Raiz url
@SessionAttributes({"compra"})
public class CompraController {

    @Autowired
    IArticuloService artServ;

    @Autowired
    ICompraService compService;
    
    @Autowired
    IProveedorService proveedorService;

    @GetMapping({"/listado"})
    public String verListado(Model model) {

        model.addAttribute("titulo", "Listado de Compras");
        model.addAttribute("compras", compService.buscarTodo());
        model.addAttribute("proveedores", proveedorService.buscarTodo());
        return "compras/list";
    }

    @GetMapping("/nueva")
    public String nuevaCompra(Model model) {

        Compra compra = new Compra();
        compra.setFechaHora(LocalDateTime.now());

        model.addAttribute("titulo", "Nueva Compra");
        model.addAttribute("tituloForm", "Completar los Campos");
        model.addAttribute("proveedor", proveedorService.buscarTodo());
        model.addAttribute("compra", compra);

        return "compras/form";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable long id, Model model, RedirectAttributes redirect) {
        compService.borrar(id);
        redirect.addFlashAttribute("danger", "Compra Eliminada..");

        return "redirect:/compras/listado";
    }

    @PostMapping("/guardar")
    public String guardarCompra(@Valid Compra compra, BindingResult result,
            @RequestParam(name = "item_id[]", required = true) List<String> itemId,
            @RequestParam(name = "precio_actual[]", required = true) List<String> precioActual,
            @RequestParam(name = "cantidad[]", required = true) List<String> cantidad,
            Model model, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            flash.addAttribute("error", "Error existente");
            return "compras/form";
        }

        if (itemId == null || itemId.size() == 1) {
            model.addAttribute("titulo", " Añadir Articulos");
            model.addAttribute("error", " La Compra no esta registrada");

            return "compras/form";
        }
        LineaCompra linea = new LineaCompra();
        Articulo articulo = new Articulo();

        for (int i = 1; i < itemId.size(); i++) {
            linea = new LineaCompra();
            Long id = Long.parseLong(itemId.get(i));

            articulo = artServ.buscarPorId(id);

            linea.setArticulo(articulo);
            linea.setCantidad(Integer.parseInt(cantidad.get(i)));

            compra.addLinea(linea);

        }

        compService.guardar(compra);
        status.setComplete();
        flash.addFlashAttribute("success", "Muchas Gracias");
        return "redirect:/compras/nueva";
    }

    @ModelAttribute("articulos")
    public List<Articulo> getArticulos() {
        return artServ.buscarTodo();
    }

}
