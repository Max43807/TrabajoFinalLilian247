package com.analistas.ventas.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analistas.ventas.model.domain.Deudor;
import com.analistas.ventas.model.repository.IDeudoresRepository;

@Service
public class DeudoresServiceImpl implements IDeudoresService {

	@Autowired 
	IDeudoresRepository repo;
	
	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<Deudor> buscarTodo() {
		return repo.findAll();
	}

	@Override
	public Deudor buscaPor(String texto) {
		return null;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Deudor buscarPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void guardar(Deudor deudores) {
		repo.save(deudores);
		
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void borrarPorId(Long id) {
		repo.deleteById(id);
		
	}

}
