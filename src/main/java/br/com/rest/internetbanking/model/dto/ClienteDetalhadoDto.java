package br.com.rest.internetbanking.model.dto;

import br.com.rest.internetbanking.model.Cliente;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;

public class ClienteDetalhadoDto {

    private Long id;
    private String nome;
    private boolean planoExclusive;
    private BigDecimal saldo;
    private String numeroConta;
    private Date dataNascimento;

    public ClienteDetalhadoDto(){}

    public ClienteDetalhadoDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.planoExclusive = cliente.hasPlanoExclusive();
        this.saldo = cliente.getSaldo();
        this.numeroConta = cliente.getNumeroConta();
        this.dataNascimento = cliente.getDataNascimento();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPlanoExclusive() {
        return planoExclusive;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public static Page<ClienteDetalhadoDto> converter(Page<Cliente> cliente) {
        return cliente.map(ClienteDetalhadoDto::new);
    }
}
