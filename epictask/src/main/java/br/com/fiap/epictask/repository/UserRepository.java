package br.com.fiap.epictask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Signup;

public interface UserRepository extends JpaRepository<Signup, Long> {

	Page<Signup> findByUsernameContaining(String username, Pageable pageable);

}
