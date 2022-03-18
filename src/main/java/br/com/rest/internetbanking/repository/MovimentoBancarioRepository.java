package br.com.rest.internetbanking.repository;

import br.com.rest.internetbanking.model.MovimentoBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoBancarioRepository extends JpaRepository<MovimentoBancario, Long> {
}
