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

    private void insereNodo(Nodo novoNodo, Nodo nodoAtual) throws NodoInvalidoException {
        Nodo filhoAEsquerda = nodoAtual.getEsquerda();
        Nodo filhoADireita = nodoAtual.getDireita();
        if (Objects.isNull(novoNodo)) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo nulo.");
        } else if (Objects.isNull(novoNodo.getChave())) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo com chave vazia.");
        }else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) < 0) {
            if (filhoAEsquerda.getChave() == null) {
                novoNodo.setAltura(nodoAtual.getAltura() + 1);
                nodoAtual.setEsquerda(novoNodo);
            } else {
                insereNodo(novoNodo, filhoAEsquerda);
            }
        } else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) > 0) {
            if (filhoADireita.getChave() == null) {
                nodoAtual.setDireita(novoNodo);
                //nodoAtual.setAltura();
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

    private Nodo rotacaoADireita(Nodo nd) {
        Nodo e = nd.getEsquerda();
        nd.setEsquerda(e.getDireita());
        e.setDireita(nd);
        //nd.height = Math.max(height(nd.left), height(nd.right)) + 1;
        //e.height = Math.max(height(e.left), height(e.right)) + 1;
        return e;
    }

}
