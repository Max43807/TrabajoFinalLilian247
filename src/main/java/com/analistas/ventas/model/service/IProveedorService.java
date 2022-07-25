
package com.analistas.ventas.model.service;


import java.util.List;

import com.analistas.ventas.model.domain.Proveedor;

public interface IProveedorService {
    
    public List<Proveedor> buscarTodo();
    
    public Proveedor buscarPorId(long id);
    
    public void guardar(Proveedor proveedor);
    
    public void borrar(long id);
    
    public Long count(boolean activo);
    
}
