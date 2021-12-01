package com.analistas.ventas.model.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.analistas.ventas.model.domain.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);

}
