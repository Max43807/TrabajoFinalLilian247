
package com.analistas.ventas.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analistas.ventas.model.domain.Proveedor;

public interface IProveedorRepository  extends JpaRepository<Proveedor, Long>{
	
	@Query(value = "SELECT count(id) FROM Proveedor where activo = :activo")
    public Long count(@Param("activo") boolean activo);
    
}
