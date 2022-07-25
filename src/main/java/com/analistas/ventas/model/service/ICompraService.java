
package com.analistas.ventas.model.service;


import java.util.List;

import com.analistas.ventas.model.domain.Compra;

public interface ICompraService {
    
    public List<Compra> buscarTodo();
    
    public Compra buscarPor(String texto);
    
    public Compra buscarPorId(long id);
    
    public void guardar(Compra compra);
    
    public void borrar(long id);
    
    public Long count(boolean activo);
    
}
