/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.model.service;

import com.analistas.ventas.model.domain.Articulo;
import com.analistas.ventas.model.repository.IArticuloRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements IArticuloService{
    
    @Autowired
    IArticuloRepository repo;

    @Override
    @Transactional(readOnly = true)
    public List<Articulo> buscarTodo() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo buscarPor(String texto) {
        return repo.findByDescripcion(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional 
    public void guardar(Articulo articulo) {
        repo.save(articulo);
    }

    @Override
    @Transactional
    public void borrarPorId(Long id) {
        repo.deleteById(id);
    }
    
    @Override
    public Long count(boolean activo) {
        return repo.count(activo);
    }
    
}
