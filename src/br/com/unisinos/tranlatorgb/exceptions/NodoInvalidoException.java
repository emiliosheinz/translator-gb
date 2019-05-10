package br.com.unisinos.tranlatorgb.exceptions;

public class NodoInvalidoException extends Exception {

    String mensagem;

    public NodoInvalidoException(String mensagem){
        this.mensagem = mensagem;
    }

}
