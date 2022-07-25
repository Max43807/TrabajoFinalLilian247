package com.analistas.ventas.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.analistas.ventas.model.domain.User;
import com.analistas.ventas.model.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repository;
	
	@Autowired
	 BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @Autowired
	 PasswordEncoder passwordEncoder;

	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
		
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirm Password es obligatorio");
		}
		
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}


	@Override
	 public User createUser(User user) throws Exception {

		
	  
	  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	  
	  if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
	   
	   /*modificar el password para que sea seguro*/ 
	   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	   /*modificar el password para que sea seguro*/ 
	   user = repository.save(user);
	   
	   
	  }
	  return user;
	 }
	   
	   
	@Transactional(readOnly = true)
    @Override
    public User buscarPorId(long id) {
        return repository.findById(id).orElse(null);
    }


	@Override
	public User getUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario para editar no existe."));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}

	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}
	
	public void deleteUser(Long id) throws Exception {
		User user = repository.findById(id)
				.orElseThrow(() -> new Exception("UsernotFound in deleteUser -"+this.getClass().getName()));

		repository.delete(user);
	}
	
	 @Override
	    public Long count(boolean activo) {
	       return repository.count(activo);
	    }
	
}
