

package com.analistas.ventas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analistas.ventas.model.domain.Compra;

public interface ICompraRepository  extends JpaRepository<Compra, Long>{
    
    public Compra findByDescripcion(String criterio);
    
}
