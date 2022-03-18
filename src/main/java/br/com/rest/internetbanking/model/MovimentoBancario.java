package br.com.rest.internetbanking.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class MovimentoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Transacao transacao;

    @Temporal(TemporalType.DATE)
    private Date data;

    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public MovimentoBancario(){}

    public MovimentoBancario(Transacao transacao, BigDecimal valor) {
        this.transacao = transacao;
        this.valor = valor;
        this.data = new Date();
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }
}
