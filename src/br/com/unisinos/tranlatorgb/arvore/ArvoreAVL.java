package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.Dicionario;
import br.com.unisinos.tranlatorgb.exceptions.NodoInvalidoException;

import java.util.List;
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
        Nodo nodo = pesquisaValor(novoNodo.getChave().getPalavra(), nodoAtual);

        if (Objects.nonNull(nodo)) {
            List<String> traducoesExistente = nodo.getChave().getDefinicoes();
            List<String> novasTraducoes = novoNodo.getChave().getDefinicoes();
            List<String> futurasTraducoes = traducoesExistente;

            for (int i = 0; i < novasTraducoes.size(); i++) {
                if (!traducoesExistente.contains(novasTraducoes.get(i))) {
                    futurasTraducoes.add(novasTraducoes.get(i));
                }
            }

            novoNodo.getChave().setDefinicoes(futurasTraducoes);

            insereNodo(novoNodo, nodoAtual, true);
        } else {
            insereNodo(novoNodo, nodoAtual, false);
        }
    }

    public List<Dicionario> preOrdem(Nodo raizDaArvore, List<Dicionario> ordemDeLeitura) {
        if (raizDaArvore.getChave() != null) {
            ordemDeLeitura.add(raizDaArvore.getChave());
            preOrdem(raizDaArvore.getEsquerda(), ordemDeLeitura);
            preOrdem(raizDaArvore.getDireita(), ordemDeLeitura);
        }

        return ordemDeLeitura;
    }

    public List<Dicionario> emOrdem(Nodo raizDaArvore, List<Dicionario> ordemDeLeitura) {
        if (raizDaArvore.getChave() != null) {
            emOrdem(raizDaArvore.getEsquerda(), ordemDeLeitura);
            ordemDeLeitura.add(raizDaArvore.getChave());
            emOrdem(raizDaArvore.getDireita(), ordemDeLeitura);
        }

        return ordemDeLeitura;
    }

    public List<Dicionario> posOrdem(Nodo raizDaArvore, List<Dicionario> ordemDeLeitura) {
        if (raizDaArvore.getChave() != null) {
            posOrdem(raizDaArvore.getEsquerda(), ordemDeLeitura);
            posOrdem(raizDaArvore.getDireita(), ordemDeLeitura);
            ordemDeLeitura.add(raizDaArvore.getChave());
        }

        return ordemDeLeitura;
    }

    private void insereNodo(Nodo novoNodo, Nodo nodoAtual, boolean atualizacao) throws NodoInvalidoException {
        if (Objects.isNull(novoNodo)) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo nulo.");
        } else if (Objects.isNull(novoNodo.getChave())) {
            throw new NodoInvalidoException("Nodo inválido, foi passado como parâmetro um nodo com chave vazia.");
        } else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) < 0) {
            if (!atualizacao) {
                nodoAtual.setBalanceamento(nodoAtual.getBalanceamento() + 1);
            }
            if (nodoAtual.getEsquerda().getChave() == null) {
                nodoAtual.setEsquerda(novoNodo);
            } else {
                insereNodo(novoNodo, nodoAtual.getEsquerda(), atualizacao);
            }
        } else if (novoNodo.getChave().getPalavra().compareToIgnoreCase(nodoAtual.getChave().getPalavra()) > 0) {
            if (!atualizacao) {
                nodoAtual.setBalanceamento(nodoAtual.getBalanceamento() - 1);
            }
            if (nodoAtual.getDireita().getChave() == null) {
                nodoAtual.setDireita(novoNodo);
            } else {
                insereNodo(novoNodo, nodoAtual.getDireita(), atualizacao);
            }
        } else {
            nodoAtual.setChave(novoNodo.getChave());
        }


    }

    public Nodo pesquisaValor(String valor, Nodo raizArvore) {
        if (Objects.isNull(raizArvore.getChave())) return null;
        if (raizArvore.getChave().getPalavra().compareToIgnoreCase(valor) > 0) {
            return pesquisaValor(valor, raizArvore.getEsquerda());
        }
        if (raizArvore.getChave().getPalavra().compareToIgnoreCase(valor) < 0) {
            return pesquisaValor(valor, raizArvore.getDireita());
        }

        return raizArvore;
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
