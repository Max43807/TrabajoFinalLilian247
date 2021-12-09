
package com.analistas.ventas.model.service;


import com.analistas.ventas.model.domain.Proveedor;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.analistas.ventas.model.repository.IProveedorRepository;
/**
 *
 * @author jorge
 */
@Service
public class ProveedorServiceImpl implements IProveedorService{
    
    @Autowired
    IProveedorRepository proveeRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Proveedor> buscarTodo() {
      return (List<Proveedor>) proveeRepo.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public Proveedor buscarPorId(long id) {
        return proveeRepo.findById(id).orElse(null);
    }
    
    @Transactional
    @Override
    public void guardar(Proveedor proveedor) {
       proveeRepo.save(proveedor);
    }

    @Transactional
    @Override
    public void borrar(long id) {
      proveeRepo.deleteById(id);
    }
}
