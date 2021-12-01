/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.model.service;

import com.analistas.ventas.model.domain.Venta;
import java.util.List;

/**
 *
 * @author ander
 */
public interface IVentaService {
    
    public List<Venta> listarTodo();
    
    public Venta buscarPorId(Long id);
    
    public void guardar(Venta venta);
}
