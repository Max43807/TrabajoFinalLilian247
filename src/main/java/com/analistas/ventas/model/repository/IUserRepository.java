package com.analistas.ventas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.analistas.ventas.model.domain.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);
	
	public User findByUser(String username);
	@Query(value = "SELECT count(id) FROM User where activo = :activo")
    public Long count(@Param("activo") boolean activo);

	
}
