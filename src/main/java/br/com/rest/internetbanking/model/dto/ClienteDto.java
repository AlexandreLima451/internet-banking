package br.com.rest.internetbanking.model.dto;

import br.com.rest.internetbanking.model.Cliente;
import org.springframework.data.domain.Page;

public class ClienteDto {

    private Long id;
    private String nome;
    private String numeroConta;

    public ClienteDto(){}

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.numeroConta = cliente.getNumeroConta();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public static Page<ClienteDto> converter(Page<Cliente> cliente) {
        return cliente.map(ClienteDto::new);
    }
}
