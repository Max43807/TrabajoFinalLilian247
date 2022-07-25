package com.analistas.ventas.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analistas.ventas.model.domain.Caja;

public interface ICajaRepository extends JpaRepository<Caja, Long> {

	

	public Caja activo(long id);

	@Query(value = "SELECT count(id) FROM Caja where activo = :activo")
    public Long count(@Param("activo") boolean activo);

    @Query("select c from Caja c where c.activo = true")
    public List<Caja> findByActiveTrue();

}
