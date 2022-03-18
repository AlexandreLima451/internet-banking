package br.com.rest.internetbanking.service;

import br.com.rest.internetbanking.model.MovimentoBancario;
import br.com.rest.internetbanking.repository.MovimentoBancarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private MovimentoBancarioRepository repository;

    public Page<MovimentoBancario> listarTransacoesPorData(Date data, Pageable paginacao){
        return repository.findByData(data, paginacao);
    }
}
