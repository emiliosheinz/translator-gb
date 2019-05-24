package br.com.unisinos.tranlatorgb.exceptions;

public class ChaveInvalidaException extends Exception {

    private String mensagem;

    public ChaveInvalidaException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
