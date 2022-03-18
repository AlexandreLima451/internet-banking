package br.com.rest.internetbanking.model.dto;

import java.math.BigDecimal;

public class NovaTransacaoDto {

    private Long clienteId;
    private BigDecimal valor;

    public Long getClienteId() {
        return clienteId;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
