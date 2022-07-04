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
