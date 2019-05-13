package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.exceptions.NodoInvalidoException;

import java.util.Objects;

public class ArvoreAVL {

    Nodo raiz;

    public ArvoreAVL(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void insereNodo(Nodo novoNodo, Nodo nodoAtual) throws NodoInvalidoException {
        if (Objects.isNull(novoNodo)) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo nulo.");
        } else if (Objects.isNull(novoNodo.getChave())) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo com chave vazia.");
        } else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) == 0) {
            throw new NodoInvalidoException("Não é possível inserir este nodo na árvoce, chave ja existe.");
        } else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) < 0) {
            Nodo nodoEsquerda = nodoAtual.getEsquerda();
            if (nodoEsquerda == null) {
                nodoAtual.setEsquerda(novoNodo);
            } else {
                insereNodo(novoNodo, nodoEsquerda);
            }
        } else {
            Nodo nodoDireita = nodoAtual.getDireita();
            if (nodoDireita == null) {
                nodoAtual.setDireita(novoNodo);
            } else {
                insereNodo(novoNodo, nodoDireita);
            }
        }
    }

}
