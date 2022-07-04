package com.analistas.ventas.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.analistas.ventas.model.domain.LineaCompra;
import com.analistas.ventas.model.repository.ILineaCompraRepository;

@Service
public class LineaCompraServiceImpl implements ILineaCompraService{

   @Autowired
   ILineaCompraRepository LinComRepo;
   
  @Transactional(readOnly = true)
  @Override
   public List<LineaCompra> buscarTodo() {
     return LinComRepo.findAll();
     }
   
  @Transactional(readOnly = true)
 @Override
   public LineaCompra buscarPor(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    @Transactional(readOnly = true)
   @Override
    public LineaCompra buscarPorId(long id) {
      return LinComRepo.findById(id).orElse(null);
    }
     
    @Transactional
    @Override
    public void guardar(LineaCompra lineas) {
        LinComRepo.save(lineas);
    }

    @Transactional
    @Override
    public void borrar(long id) {
        LinComRepo.deleteById(id);
    }
   
}
