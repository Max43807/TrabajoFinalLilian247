package com.analistas.ventas.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analistas.ventas.model.domain.Venta;
import com.analistas.ventas.model.domain.Caja;
import com.analistas.ventas.model.domain.User;
import com.analistas.ventas.model.repository.IUserRepository;
import com.analistas.ventas.model.repository.IVentaRepository;
import com.analistas.ventas.model.service.IArticuloService;
import com.analistas.ventas.model.service.ICajaService;
import com.analistas.ventas.model.service.ICompraService;
import com.analistas.ventas.model.service.IProveedorService;
import com.analistas.ventas.model.service.IUserService;
import com.analistas.ventas.model.service.IVentaService;



@Controller
@RequestMapping("/cajas") 
@SessionAttributes({"caja"})
public class CajaController {

    @Autowired
    ICajaService cajaService;

    @Autowired
    IVentaService ventaService;
    
    @Autowired
    IVentaRepository ventaRepository;
    
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
 

    @GetMapping({"/listado"})
    public String verListado(Model model) {

    	model.addAttribute("titulo", "Historial de Cajas y Movimientos");
        model.addAttribute("cajas", cajaService.buscarTodo());
        model.addAttribute("ventas", ventaService.buscarTodo());
        model.addAttribute("compras", compraService.buscarTodo());

        return "cajas/list";
    }

    @GetMapping("/nuevo")
    public ResponseEntity<?> nuevo(Model m) {

        Caja caja = new Caja();
        return ResponseEntity.ok(caja);
    }
    
    @GetMapping("/cambiar/{id}")
    public String cambiar(@PathVariable("id") long id) {

        Caja c = cajaService.buscarPorId(id);
        c.setActivo(!c.isActivo());
//        a.setActivo(!a.isActivo());
        cajaService.guardar(c);

        return "redirect:/cajas/listado";

    }

    @PostMapping("/inicial")
    public ResponseEntity<?> cajaInicial(@Valid Caja caja, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, Principal principal) {

        //Construir un diccionario de errores...
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        caja.setUsuario(getUsuario(principal));
        caja.setFecha_Hora_apertura(LocalDateTime.now());
        cajaService.guardar(caja);

        status.isComplete();
//        model.addAttribute("error", " Debes Completar el Formulario...");
        flash.addFlashAttribute("success", "Caja Iniciado correctamente...");
        return ResponseEntity.ok().build();
    }


    @PostMapping("/guardar")
    public String guardar(@Valid Caja caja, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, Venta venta) {

        if (result.hasErrors()) {
            flash.addAttribute("error", "Error existente");
//            model.addAttribute("info", " Añadir Articulos");
            model.addAttribute("error", " Debes Completar el Formulario...");
            return "cajas/form";
        }

        if (caja.getId() == 0) {
            flash.addFlashAttribute("success", " La Caja " + caja.getMontoFinal() + " Fue Creado con Exitos... ");
//            model.addAttribute("titulo", " Añadir Ar..0 ticulos");
        } else {
            flash.addFlashAttribute("warning", " La Caja " + caja.getMontoFinal() + " Fue Modificado con Exitos... ");
            model.addAttribute("error", " Debes Completar el Formulario...");
        }

//        caja.addTotal(total);
//        caja.getTotal();
//        caja.getTotales();
//        caja.getTotal_ventas();
//        caja.setCajaTotal(caja.getMontoInicial() + caja.getMontoFinal());
        caja.setCajaTotal(caja.getMontoInicial() + caja.getMontoFinal()+ caja.getTotalVentasCaja()- caja.getTotalComprasCaja());
        caja.setActivo(!caja.isActivo());
        caja.setFecha_Hora_cierre(LocalDateTime.now());

        cajaService.guardar(caja);
//        ventaService.guardar(venta);
        status.isComplete();

        flash.addFlashAttribute("success", " Caja Guardado con Éxitos...");
        return "redirect:/ventas/nueva";
    }

    @GetMapping("/cierre/{id}")
    public String cierreCaja(@PathVariable long id, Model model, RedirectAttributes redirect) {

        model.addAttribute("titulo", "Finalizar Caja");
        model.addAttribute("tituloForm", "Editar los Campos");
        model.addAttribute("caja", cajaService.buscarPorId(id));

        return "cajas/form";
    }
    
    private User getUsuario(Principal principal) {
        User usuario = cajaService.buscarPorUser(principal.getName());
        return usuario;
    }
}
