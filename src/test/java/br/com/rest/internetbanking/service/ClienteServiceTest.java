package br.com.rest.internetbanking.service;

import br.com.rest.internetbanking.model.Cliente;
import br.com.rest.internetbanking.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteServiceTest {

    private Cliente cliente;

    private ClienteService service;

    @Mock
    private ClienteRepository repository;

    @BeforeEach
    public void inicializar(){
        MockitoAnnotations.openMocks(this);
        cliente = criarCliente();
        service = new ClienteService(repository);
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

    @Test
    public void sacarValorAcimaDoSaldoDisponivel(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.sacar(cliente, new BigDecimal("400.00")));
    }

    private Cliente criarCliente()  {
        Date dataNascimento;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataNascimento = sdf.parse("1998-04-12");
        }catch ( ParseException ex) {
            dataNascimento = new Date();
        }

        return new Cliente("Carlos", false, "123456", dataNascimento);
    }
}
