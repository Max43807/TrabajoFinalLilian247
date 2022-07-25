

package com.analistas.ventas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analistas.ventas.model.domain.Compra;

public interface ICompraRepository  extends JpaRepository<Compra, Long>{
    
    public Compra findByDescripcion(String criterio);
    
    @Query(value = "SELECT count(id) FROM Compra where activo = :activo")
    public Long count(@Param("activo") boolean activo);
    
}
