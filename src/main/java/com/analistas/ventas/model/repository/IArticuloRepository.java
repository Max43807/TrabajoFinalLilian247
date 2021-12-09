package com.analistas.ventas.model.repository;

import com.analistas.ventas.model.domain.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ander
 */
public interface IArticuloRepository extends JpaRepository<Articulo, Long> {
    
    public Articulo findByDescripcion(String criterio);
}
