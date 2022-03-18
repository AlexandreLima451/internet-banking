package br.com.rest.internetbanking.model.dto;

import java.math.BigDecimal;

public class NovoClienteDto {

    private String nome;
    private boolean planoExclusive;
    private String numeroConta;
    private String dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPlanoExclusive() {
        return planoExclusive;
    }

    public void setPlanoExclusive(boolean planoExclusive) {
        this.planoExclusive = planoExclusive;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
