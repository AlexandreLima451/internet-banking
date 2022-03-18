package br.com.rest.internetbanking.repository;

import br.com.rest.internetbanking.model.MovimentoBancario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimentoBancarioRepository extends JpaRepository<MovimentoBancario, Long> {

    Page<MovimentoBancario> findByData(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataHora, Pageable paginacao);
}
