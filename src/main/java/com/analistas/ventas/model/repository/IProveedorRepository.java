
package com.analistas.ventas.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.analistas.ventas.model.domain.Proveedor;

public interface IProveedorRepository  extends JpaRepository<Proveedor, Long>{
    
}
