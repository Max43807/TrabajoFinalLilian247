/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.analistas.ventas.model.service;


import java.util.List;

import com.analistas.ventas.model.domain.LineaCompra;

public interface ILineaCompraService {
    
    public List<LineaCompra> buscarTodo();
    
    public LineaCompra buscarPor(String texto);
    
    public LineaCompra buscarPorId(long id);

    public void guardar(LineaCompra lineas);
    
    public void borrar(long id);
    
}
