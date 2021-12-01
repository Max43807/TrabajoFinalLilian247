package com.analistas.ventas.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.analistas.ventas.model.domain.Role;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {

}
