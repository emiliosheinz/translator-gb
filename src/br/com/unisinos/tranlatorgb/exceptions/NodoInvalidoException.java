package br.com.unisinos.tranlatorgb.exceptions;

public class NodoInvalidoException extends Exception {

    private String mensagem;

    public NodoInvalidoException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
