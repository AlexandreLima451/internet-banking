package br.com.rest.internetbanking.controller;

import br.com.rest.internetbanking.model.MovimentoBancario;
import br.com.rest.internetbanking.model.dto.MovimentoBancarioDto;
import br.com.rest.internetbanking.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping
    public ResponseEntity<Page<MovimentoBancarioDto>> listarPorData(@RequestParam(value="data") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data,
                                           @PageableDefault(page = 0, size = 5) Pageable paginacao){

        Page<MovimentoBancario> movimentacoes = service.listarTransacoesPorData(data, paginacao);
        return ResponseEntity.ok(MovimentoBancarioDto.converter(movimentacoes));
    }
}
