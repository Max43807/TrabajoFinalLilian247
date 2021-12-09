package com.analistas.ventas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analistas.ventas.model.domain.Deudor;

public interface IDeudoresRepository extends JpaRepository<Deudor, Long> {

}
