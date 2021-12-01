package com.analistas.ventas.model.service;

import com.analistas.ventas.model.domain.User;

public interface IUserService {

	public Iterable<User> getAllUsers();
	
	public User createUser(User user) throws Exception;
	
	public User getUserById(Long id) throws Exception;

	public User updateUser(User user) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
	
}
