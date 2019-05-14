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

    public void insereNodoComVerificacao(Nodo novoNodo, Nodo nodoAtual) throws NodoInvalidoException {
        if(pesquisaValor(novoNodo.getChave().getPalavra(), nodoAtual)){
            throw new NodoInvalidoException("Nodo inválido, esta chave ja está registrada nesta árvore.");
        }else {
            insereNodo(novoNodo, nodoAtual);
        }
    }

    public void preOrdem(Nodo raizDaArvore){
        if(raizDaArvore.getChave() != null){
            System.out.println(raizDaArvore.getChave().getPalavra());
            preOrdem(raizDaArvore.getEsquerda());
            preOrdem(raizDaArvore.getDireita());
        }
    }

    public void emOrdem(Nodo raizDaArvore){
        if(raizDaArvore.getChave() != null){
            emOrdem(raizDaArvore.getEsquerda());
            System.out.println(raizDaArvore.getChave().getPalavra());
            emOrdem(raizDaArvore.getDireita());
        }
    }

    public void posOrdem(Nodo raizDaArvore){
        if(raizDaArvore.getChave() != null){
            posOrdem(raizDaArvore.getEsquerda());
            posOrdem(raizDaArvore.getDireita());
            System.out.println(raizDaArvore.getChave().getPalavra());
        }
    }

    private void insereNodo(Nodo novoNodo, Nodo nodoAtual) throws NodoInvalidoException {
        if (Objects.isNull(novoNodo)) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo nulo.");
        } else if (Objects.isNull(novoNodo.getChave())) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo com chave vazia.");
        }else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) < 0) {
            if (nodoAtual.getEsquerda().getChave() == null) {
                nodoAtual.setEsquerda(novoNodo);
            } else {
                insereNodo(novoNodo, nodoAtual.getEsquerda());
            }
        } else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) > 0) {
            if (nodoAtual.getDireita().getChave() == null) {
                nodoAtual.setDireita(novoNodo);
            } else {
                insereNodo(novoNodo, nodoAtual.getDireita());
            }
        }
    }

    private boolean pesquisaValor(String valor, Nodo raizArvore) {
        if (raizArvore.getChave() == null) return false;
        if (raizArvore.getChave().getPalavra().compareToIgnoreCase(valor) < 0) {
            return pesquisaValor(valor, raizArvore.getEsquerda());
        }
        if (raizArvore.getChave().getPalavra().compareToIgnoreCase(valor) > 0) {
            return pesquisaValor(valor, raizArvore.getDireita());
        }

        return true;
    }

}
