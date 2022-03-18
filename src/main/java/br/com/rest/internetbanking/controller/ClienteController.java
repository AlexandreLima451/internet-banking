package br.com.rest.internetbanking.controller;

import br.com.rest.internetbanking.model.dto.ClienteDetalhadoDto;
import br.com.rest.internetbanking.model.dto.ClienteDto;
import br.com.rest.internetbanking.model.dto.NovaTransacaoDto;
import br.com.rest.internetbanking.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteDto>> buscarTodos(@PageableDefault(page = 0, size = 5) Pageable paginacao){
        Page<ClienteDto> clientes = service.buscarTodos(paginacao);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetalhadoDto> buscar(@PathVariable Long id){
        ClienteDetalhadoDto cliente = service.buscar(id);
        if(cliente != null){
            return ResponseEntity.ok(cliente);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deposito")
    public ResponseEntity<?> depositar(@RequestBody NovaTransacaoDto movimentoBancarioDto){

        service.realizarDepositoComIdCliente(movimentoBancarioDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saque")
    public ResponseEntity<?> sacar(@RequestBody NovaTransacaoDto movimentoBancarioDto){

        service.realizarSaqueComIdCliente(movimentoBancarioDto);
        return ResponseEntity.ok().build();
    }


}