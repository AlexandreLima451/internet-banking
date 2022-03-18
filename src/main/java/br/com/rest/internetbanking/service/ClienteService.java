package br.com.rest.internetbanking.service;

import br.com.rest.internetbanking.model.Cliente;
import br.com.rest.internetbanking.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public void sacar(Cliente cliente, BigDecimal valor) {
        valor = aplicarTaxa(cliente, valor);
        cliente.subtrairSaldo(valor);
        repository.save(cliente);
    }

    private BigDecimal aplicarTaxa(Cliente cliente, BigDecimal valor){
        BigDecimal taxa = BigDecimal.ZERO;

        if(!cliente.isPlanoExclusive()){
            if(valor.compareTo(new BigDecimal("100.0")) > 0 && valor.compareTo(new BigDecimal("300.0")) <= 0){
                taxa = valor.multiply(new BigDecimal("0.04"));
            }else if(valor.compareTo(new BigDecimal("300.0")) > 0) {
                taxa = valor.multiply(new BigDecimal("0.1"));
            }
        }
        valor = valor.add(taxa);

        return valor;
    }

    public void depositar(Cliente cliente, BigDecimal valor) {
        cliente.aumentarSaldo(valor);
        repository.save(cliente);
    }
}
