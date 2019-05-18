package br.com.unisinos.tranlatorgb.exceptions;

public class PalavraNaoEncontradaException extends Exception {

    private String mensagem;

    public PalavraNaoEncontradaException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}
