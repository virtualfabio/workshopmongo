package com.creature.binary.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creature.binary.workshopmongo.domain.User;
import com.creature.binary.workshopmongo.dto.UserDTO;
import com.creature.binary.workshopmongo.repository.UserRepository;
import com.creature.binary.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
	}
	
	public User insert(User user) {
		return repo.insert(user);
	
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
		
	}

}
