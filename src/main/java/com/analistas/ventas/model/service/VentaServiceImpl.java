/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.model.service;

import com.analistas.ventas.model.repository.ICajaRepository;
import com.analistas.ventas.model.domain.Venta;
import com.analistas.ventas.model.repository.IVentaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    IVentaRepository ventaRepo;
    
    @Autowired
    ICajaRepository cajaRepository;
    
    @Transactional(readOnly = true)
    @Override
    public List<Venta> buscarTodo() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void guardar(Venta venta) {
        ventaRepo.save(venta);
    }
    
    @Transactional
    @Override
    public void borrar(long id) {
        ventaRepo.deleteById(id);
    }
    
    @Override
    public Long count(boolean activo) {
        return ventaRepo.count(activo);
    }
    
    @Override
    public void activo(long id) {
        cajaRepository.activo(id);
    }
}
