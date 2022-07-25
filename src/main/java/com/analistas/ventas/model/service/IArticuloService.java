/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.model.service;

import com.analistas.ventas.model.domain.Articulo;
import java.util.List;

public interface IArticuloService {
    
    public List<Articulo> buscarTodo();
    
    public Articulo buscarPor(String texto);
    
    public Articulo buscarPorId(Long id);
    
    public void guardar(Articulo articulo);
    
    public void borrarPorId(Long id);
    
    public Long count(boolean activo);
}
