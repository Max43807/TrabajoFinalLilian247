package com.analistas.ventas.model.service;


import java.util.List;


import com.analistas.ventas.model.domain.Caja;
import com.analistas.ventas.model.domain.User;

public interface ICajaService {

    public List<Caja> buscarTodo();




    public Caja buscarPorId(long id);

    public void activo(long id);

    public void guardar(Caja caja);

    public void borrar(long id);

    public Long count(boolean activo);
    
    public User buscarPorUser(String username);
}
