package br.com.rest.internetbanking.config.validacao;

public class ErroDeFormularioDto {

    private String erro;

    public ErroDeFormularioDto(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
}
