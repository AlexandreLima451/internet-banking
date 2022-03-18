package br.com.rest.internetbanking.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class MovimentoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Transacao transacao;

    private LocalDateTime dataEHora;

    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public MovimentoBancario(Transacao transacao, BigDecimal valor) {
        this.transacao = transacao;
        this.valor = valor;
        this.dataEHora = LocalDateTime.now();
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

    public LocalDateTime getDataEHora() {
        return dataEHora;
    }
}
