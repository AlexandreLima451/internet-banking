package br.com.rest.internetbanking.service;

import br.com.rest.internetbanking.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteServiceTest {

    private Cliente cliente;
    private ClienteService service;

    @BeforeEach
    public void inicializar(){
        cliente = criarCliente();
        service = new ClienteService();
        service.depositar(cliente, new BigDecimal("400.00"));
    }

    @Test
    public void sacarMenosDeCemReais(){
        service.sacar(cliente, new BigDecimal("99.90"));
        Assertions.assertEquals(new BigDecimal("300.10"), cliente.getSaldo());
    }

    @Test
    public void sacarMaisDeCemReaisEMenosDeTrezentosReais(){
        service.sacar(cliente, new BigDecimal("299.90"));
        Assertions.assertEquals(new BigDecimal("88.10"), cliente.getSaldo());
    }

    @Test
    public void sacarMaisDeTrezentosReais(){
        service.sacar(cliente, new BigDecimal("301.00"));
        Assertions.assertEquals(new BigDecimal("68.90"), cliente.getSaldo());
    }

    @Test
    public void sacarComPlanoExclusive(){
        cliente.setPlanoExclusive(true);
        service.sacar(cliente, new BigDecimal("301.00"));
        Assertions.assertEquals(new BigDecimal("99.00"), cliente.getSaldo());
    }

    private Cliente criarCliente(){
        LocalDate dataNascimento = LocalDate.of(1987, 4, 2);
        return new Cliente("Carlos", false, "123456", dataNascimento);
    }
}
