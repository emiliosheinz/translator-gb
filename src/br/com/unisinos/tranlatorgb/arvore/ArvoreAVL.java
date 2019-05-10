package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.exceptions.NodoInvalidoException;

import java.util.Objects;

public class ArvoreAVL {

    Nodo raiz;

    public void insereNodo(Nodo novoNodo) throws NodoInvalidoException {
        if(Objects.isNull(novoNodo)) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo nulo.");
        }else if(novoNodo.getChave().isEmpty()){
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo com chave vazia.");
        }
    }

}
