package com.creature.binary.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creature.binary.workshopmongo.domain.Post;
import com.creature.binary.workshopmongo.repository.PostRepository;
import com.creature.binary.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
		
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
		
	}
	
	public List<Post> searchTitle(String text){
		return repo.searchTitle(text);
		
	}
	
}
