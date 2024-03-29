package com.creature.binary.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.creature.binary.workshopmongo.domain.Post;
import com.creature.binary.workshopmongo.domain.User;
import com.creature.binary.workshopmongo.dto.AuthorDTO;
import com.creature.binary.workshopmongo.dto.CommentDTO;
import com.creature.binary.workshopmongo.repository.PostRepository;
import com.creature.binary.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	public UserRepository userRepository;	
	
	@Autowired
	public PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para ...", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei Feliz hoje!", new AuthorDTO(maria));

		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2019"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Boa viagem manin!", sdf.parse("21/03/2019"), new AuthorDTO(maria));
		CommentDTO comment3 = new CommentDTO("Valeu peixe!", sdf.parse("23/03/2019"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
