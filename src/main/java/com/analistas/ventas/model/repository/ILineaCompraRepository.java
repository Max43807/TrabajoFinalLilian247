/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.analistas.ventas.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.analistas.ventas.model.domain.LineaCompra;

/**
 *
 * @author jorge
 */
public interface ILineaCompraRepository extends JpaRepository<LineaCompra, Long>{
    
}
