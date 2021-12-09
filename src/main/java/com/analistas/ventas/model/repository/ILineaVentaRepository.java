/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.model.repository;

import com.analistas.ventas.model.domain.LineaVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILineaVentaRepository extends JpaRepository<LineaVenta, Long>{
    
}
