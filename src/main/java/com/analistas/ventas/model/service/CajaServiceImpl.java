package com.analistas.ventas.model.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.analistas.ventas.model.domain.Caja;
import com.analistas.ventas.model.domain.User;
import com.analistas.ventas.model.repository.ICajaRepository;
import com.analistas.ventas.model.repository.IUserRepository;

@Service
public class CajaServiceImpl implements ICajaService {

    @Autowired
    ICajaRepository cajaRepo;
    
    @Autowired
    IVentaService venRepo;
    
    @Autowired
    IUserRepository usuRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Caja> buscarTodo() {
        return cajaRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Caja buscarPorId(long id) {
        return cajaRepo.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void guardar(Caja caja) {
        cajaRepo.save(caja);
    }

    @Transactional
    @Override
    public void borrar(long id) {
//        cajaRepo.deleteById(id);
    }

    @Override
    public void activo(long id) {
        cajaRepo.activo(id);
    }

    @Override
    public Long count(boolean activo) {
        return cajaRepo.count(activo);
    }
    
    @Transactional(readOnly = true)
    @Override
    public User buscarPorUser(String username) {
       return usuRepo.findByUser(username);
    } 
}
