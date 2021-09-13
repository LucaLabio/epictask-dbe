package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.Signup;
import br.com.fiap.epictask.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping
	@Cacheable("users")
	public Page<Signup> userindex(@RequestParam(required = false) String username,
			@PageableDefault Pageable pageable) {
		if (username == null) return repository.findAll(pageable);
		
		return repository.findByUsernameContaining(username, pageable);
	}
	
	@PostMapping
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<Signup> createuser(@RequestBody @Valid Signup user,
			UriComponentsBuilder uriBuilder) {
		repository.save(user);
		URI uri = uriBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Signup> getuser(@PathVariable Long id) {
		Optional<Signup> user = repository.findById(id);

		return ResponseEntity.of(user);
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<Signup> deleteuser(@PathVariable Long id) {
		Optional<Signup> user = repository.findById(id);
		
		if(user.isEmpty()) return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<Signup> updateuser(@PathVariable Long id, @RequestBody Signup newUser) {
		Optional<Signup> optional = repository.findById(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		Signup user = optional.get();
		user.setUsername(newUser.getUsername());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		
		repository.save(user);
		return ResponseEntity.ok(user);
	}

}
