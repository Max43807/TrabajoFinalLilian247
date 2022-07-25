package com.analistas.ventas.model.repository;

import com.analistas.ventas.model.domain.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IArticuloRepository extends JpaRepository<Articulo, Long> {
    
    public Articulo findByDescripcion(String criterio);
    
    @Query(value = "SELECT count(id) FROM Articulo where activo = :activo")
    public Long count(@Param("activo") boolean activo);
}
