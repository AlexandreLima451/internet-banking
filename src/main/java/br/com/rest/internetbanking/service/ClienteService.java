package br.com.rest.internetbanking.service;

import br.com.rest.internetbanking.model.Cliente;
import br.com.rest.internetbanking.model.dto.ClienteDetalhadoDto;
import br.com.rest.internetbanking.model.dto.ClienteDto;
import br.com.rest.internetbanking.model.dto.NovaTransacaoDto;
import br.com.rest.internetbanking.model.dto.NovoClienteDto;
import br.com.rest.internetbanking.repository.ClienteRepository;
import br.com.rest.internetbanking.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteService {


    private ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void sacar(Cliente cliente, BigDecimal valor) {

        valor = aplicarTaxa(cliente, valor);

        if(valor.compareTo(cliente.getSaldo()) > 0)
            throw new IllegalArgumentException("Cliente nao possui saldo suficiente para este saque.");

        cliente.subtrairSaldo(valor);
        repository.save(cliente);
    }

    private BigDecimal aplicarTaxa(Cliente cliente, BigDecimal valor){
        BigDecimal taxa = BigDecimal.ZERO;

        if(!cliente.hasPlanoExclusive()){
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

    public Page<ClienteDto> buscarTodos(Pageable paginacao){

        Page<Cliente> clientes = repository.findAll(paginacao);
        Page<ClienteDto> clientesDto = ClienteDto.converter(clientes);

        return clientesDto;

    }

    public ClienteDetalhadoDto buscar(Long id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){

            Cliente cliente = clienteOptional.get();
            return new ClienteDetalhadoDto(cliente);
        }

        return null;
    }

    public void realizarDepositoComIdCliente(NovaTransacaoDto movimentoBancarioDto){
        Optional<Cliente> clienteOptional = repository.findById(movimentoBancarioDto.getClienteId());
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            depositar(cliente, movimentoBancarioDto.getValor());
        }
    }

    public void realizarSaqueComIdCliente(NovaTransacaoDto movimentoBancarioDto){
        Optional<Cliente> clienteOptional = repository.findById(movimentoBancarioDto.getClienteId());
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            sacar(cliente, movimentoBancarioDto.getValor());
        }
    }

    public void cadastrarCliente(NovoClienteDto clienteDto){

        Date dataNascimento = Converter.stringToDate(clienteDto.getDataNascimento());

        Cliente cliente = new Cliente(clienteDto.getNome(),
                clienteDto.isPlanoExclusive(),
                clienteDto.getNumeroConta(), dataNascimento);

        repository.save(cliente);
    }

    public void deletarCliente(Long id){
        repository.deleteById(id);
    }
}