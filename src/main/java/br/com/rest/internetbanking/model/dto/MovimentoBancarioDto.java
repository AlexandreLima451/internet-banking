package br.com.rest.internetbanking.model.dto;

import br.com.rest.internetbanking.model.MovimentoBancario;
import br.com.rest.internetbanking.model.Transacao;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MovimentoBancarioDto {

    private Long idCliente;
    private Transacao transacao;
    private BigDecimal valor;

    public MovimentoBancarioDto(MovimentoBancario movimentoBancario){
        this.idCliente = movimentoBancario.getCliente().getId();
        this.transacao = movimentoBancario.getTransacao();
        this.valor = movimentoBancario.getValor();
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public static Page<MovimentoBancarioDto> converter(Page<MovimentoBancario> movimentacoes){
        return movimentacoes.map(MovimentoBancarioDto::new);
    }
}
