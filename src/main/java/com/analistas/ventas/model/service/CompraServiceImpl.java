
package com.analistas.ventas.model.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.analistas.ventas.model.domain.Compra;
import com.analistas.ventas.model.repository.ICompraRepository;

@Service
public class CompraServiceImpl implements ICompraService{

    @Autowired
    ICompraRepository compRepo;
    
    @Transactional(readOnly = true)
    @Override
    public List<Compra> buscarTodo() {
        return compRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Compra buscarPor(String texto) {
      return compRepo.findByDescripcion(texto);
    }

    @Transactional(readOnly = true)
    @Override
    public Compra buscarPorId(long id) {
       return compRepo.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void guardar(Compra compra) {
       compRepo.save(compra);
    }

    @Override
    public void borrar(long id) {
       compRepo.deleteById(id);
    }
    
}
