package br.com.rest.internetbanking.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private boolean planoExclusive;
    private BigDecimal saldo;
    private String numeroConta;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<MovimentoBancario> movimentacoes = new ArrayList<>();

    public Cliente(){}

    public Cliente(String nome, boolean planoExclusivo, String numeroConta, Date dataNascimento) {
        this.nome = nome;
        this.planoExclusive = planoExclusivo;
        this.numeroConta = numeroConta;
        this.dataNascimento = dataNascimento;
        this.saldo = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean hasPlanoExclusive() {
        return planoExclusive;
    }

    public void setPlanoExclusive(boolean planoExclusive) {
        this.planoExclusive = planoExclusive;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<MovimentoBancario> getMovimentacoes() {
        return movimentacoes;
    }

    public void aumentarSaldo(BigDecimal valor) {

        this.saldo = this.saldo.add(valor);
        this.saldo = this.saldo.setScale(2, RoundingMode.HALF_EVEN);

        MovimentoBancario movimento = new MovimentoBancario(Transacao.DEPOSITO, valor);
        movimento.setCliente(this);
        this.movimentacoes.add(movimento);
    }

    public void subtrairSaldo(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
        this.saldo = this.saldo.setScale(2, RoundingMode.HALF_EVEN);

        MovimentoBancario movimento = new MovimentoBancario(Transacao.SAQUE, valor);
        movimento.setCliente(this);
        this.movimentacoes.add(movimento);
    }
}
