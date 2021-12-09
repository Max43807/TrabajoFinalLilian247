package com.analistas.ventas.model.service;

import com.analistas.ventas.model.domain.Deudor;
import java.util.List;


public interface IDeudoresService {
	
	public List<Deudor> buscarTodo();
	
	public Deudor buscaPor(String texto);
	
	public Deudor buscarPorId(Long id);
	
	public void guardar(Deudor deudores);
	
	public void borrarPorId(Long id);
	

}
