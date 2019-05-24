package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.Dicionario;
import br.com.unisinos.tranlatorgb.exceptions.ChaveInvalidaException;

import java.util.List;
import java.util.Objects;

public class ArvoreAVL {

    private Nodo raiz;

    public ArvoreAVL(Nodo raiz) {
        this.raiz = raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * Verifica se a chave a ser inserida já existe, caso exista apenas insere novas definições.
     * caso não, insere a nova chave.
     *
     * @param chave Chave a ser inserida
     * @param raiz Raiz da árvore
     * @return Retorna nodo inserido ou achado na pesquisa
     * @throws ChaveInvalidaException Lançado quando a chave inserida é nula
     */
    public Nodo insereNodoComVerificacao(Dicionario chave, Nodo raiz) throws ChaveInvalidaException {
        Nodo nodo;
        if(Objects.isNull(chave)) {
            throw new ChaveInvalidaException("Chave inválida. A chave passada é nula!");
        }
        nodo = pesquisaValor(chave.getPalavra(), raiz);

        if (Objects.nonNull(nodo)) {
            List<String> traducoesExistente = nodo.getChave().getDefinicoes();

            for (String novaTraducao : chave.getDefinicoes()) {
                if (!traducoesExistente.contains(novaTraducao)) {
                    traducoesExistente.add(novaTraducao);
                }
            }
        } else {
            return insereNodo(raiz, chave);
        }
        return nodo;
    }

    /**
     * Percorre a árvore em pré ordem e retorna o seu percurso.
     * @param raizDaArvore Raiz da árvore a ser percorrida
     * @param percursoAtual Lista dos elementos percorridos até o momento
     * @return Retorna o percurso final
     */
    public List<Dicionario> preOrdem(Nodo raizDaArvore, List<Dicionario> percursoAtual) {
        if (Objects.nonNull(raizDaArvore)) {
            percursoAtual.add(raizDaArvore.getChave());
            preOrdem(raizDaArvore.getEsquerda(), percursoAtual);
            preOrdem(raizDaArvore.getDireita(), percursoAtual);
        }

        return percursoAtual;
    }

    /**
     * Percorre a árvore em ordem e retorna o seu percurso.
     * @param raizDaArvore Raiz da árvore a ser percorrida
     * @param percursoAtual Lista dos elementos percorridos até o momento
     * @return Retorna o percurso final
     */
    public List<Dicionario> emOrdem(Nodo raizDaArvore, List<Dicionario> percursoAtual) {
        if (Objects.nonNull(raizDaArvore)) {
            emOrdem(raizDaArvore.getEsquerda(), percursoAtual);
            percursoAtual.add(raizDaArvore.getChave());
            emOrdem(raizDaArvore.getDireita(), percursoAtual);
        }

        return percursoAtual;
    }

    /**
     * Percorre a árvore em pós ordem e retorna o seu percurso.
     * @param raizDaArvore Raiz da árvore a ser percorrida
     * @param percursoAtual Lista dos elementos percorridos até o momento
     * @return Retorna o percurso final
     */
    public List<Dicionario> posOrdem(Nodo raizDaArvore, List<Dicionario> percursoAtual) {
        if (Objects.nonNull(raizDaArvore)) {
            posOrdem(raizDaArvore.getEsquerda(), percursoAtual);
            posOrdem(raizDaArvore.getDireita(), percursoAtual);
            percursoAtual.add(raizDaArvore.getChave());
        }

        return percursoAtual;
    }

    /**
     * Insere uma nova chave na árvore
     * @param nodoAtual Nodo atual da recursão
     * @param chave Chave a ser inserida
     * @return Retorna nodo inserido
     */
    private Nodo insereNodo(Nodo nodoAtual, Dicionario chave) {
        if (Objects.isNull(nodoAtual)) {
            return new Nodo(chave);
        }
        String novaChavePalavra = chave.getPalavra();
        String nodoAtualChavePalavra = nodoAtual.getChave().getPalavra();

        if (novaChavePalavra.compareToIgnoreCase(nodoAtualChavePalavra) < 0) {
            nodoAtual.setEsquerda(insereNodo(nodoAtual.getEsquerda(), chave));
        } else if (novaChavePalavra.compareToIgnoreCase(nodoAtualChavePalavra) > 0) {
            nodoAtual.setDireita(insereNodo(nodoAtual.getDireita(), chave));
        } else {
            return nodoAtual;
        }

        nodoAtual.setAltura(1 + Math.max(getAlturaArvore(nodoAtual.getEsquerda()), getAlturaArvore(nodoAtual.getDireita())));

        return verificaBalanceamento(nodoAtual, novaChavePalavra);
    }

    /**
     * Verifica o balanceamento, caso esteja desbalanceado, reequilibra a árvore
     * @param nodoAtual Nodo atual da recursão
     * @param novaChavePalavra Nova palavra a ser inserida
     * @return Retorna o nodo balanceado
     */
    private Nodo verificaBalanceamento(Nodo nodoAtual, String novaChavePalavra) {
        int balanceamento = Objects.isNull(nodoAtual) ? 0 : getAlturaArvore(nodoAtual.getEsquerda()) - getAlturaArvore(nodoAtual.getDireita());

        String palavraAEsquerdaDoNodoAtual = Objects.isNull(nodoAtual.getEsquerda()) ? "" : nodoAtual.getEsquerda().getChave().getPalavra();
        String palavraADireitaDoNodoAtual = Objects.isNull(nodoAtual.getDireita()) ? "" : nodoAtual.getDireita().getChave().getPalavra();

        if (balanceamento > 1 && novaChavePalavra.compareToIgnoreCase(palavraAEsquerdaDoNodoAtual) < 0) {
            return rotacaoADireita(nodoAtual);
        } else if (balanceamento < -1 && novaChavePalavra.compareToIgnoreCase(palavraADireitaDoNodoAtual) > 0) {
            return rotacaoAEsquerda(nodoAtual);
        } else if (balanceamento > 1 && novaChavePalavra.compareToIgnoreCase(palavraAEsquerdaDoNodoAtual) > 0) {
            nodoAtual.setEsquerda(rotacaoAEsquerda(nodoAtual.getEsquerda()));
            return rotacaoADireita(nodoAtual);
        } else if (balanceamento < -1 && novaChavePalavra.compareToIgnoreCase(palavraADireitaDoNodoAtual) < 0) {
            nodoAtual.setDireita(rotacaoADireita(nodoAtual.getDireita()));
            return rotacaoAEsquerda(nodoAtual);
        }

        return nodoAtual;
    }

    /**
     * Verifica a altura do nodo
     * @param nodo Nodo a ser verificada a altura
     * @return Retorna altura do nodo
     */
    private int getAlturaArvore(Nodo nodo) {
        return Objects.isNull(nodo) ? 0 : nodo.getAltura();
    }

    /**
     * Pesquisa valor passado pelo parâmetro chave
     * @param chave Valor a ser pesquisado
     * @param raizArvore Raiz da árvore
     * @return Retorna o nodo encontrado ou null caso não encontre
     */
    public Nodo pesquisaValor(String chave, Nodo raizArvore) {
        if (Objects.isNull(raizArvore)) return null;
        if (raizArvore.getChave().getPalavra().compareToIgnoreCase(chave) > 0) {
            return pesquisaValor(chave, raizArvore.getEsquerda());
        }
        if (raizArvore.getChave().getPalavra().compareToIgnoreCase(chave) < 0) {
            return pesquisaValor(chave, raizArvore.getDireita());
        }

        return raizArvore;
    }

    /**
     * Rotaciona à direita
     * @param nodoDesbalanceado Nodo desbalanceado
     * @return Retorna o nodo rotacionado para o lugar do nodo desbalanceado
     */
    private Nodo rotacaoADireita(Nodo nodoDesbalanceado) {
        Nodo nodoDesbalanceadoEsquerda = nodoDesbalanceado.getEsquerda();
        Nodo maiorFilhoAEsquerdaDoNodoDesbalanceado = nodoDesbalanceadoEsquerda.getDireita();

        nodoDesbalanceadoEsquerda.setDireita(nodoDesbalanceado);
        nodoDesbalanceado.setEsquerda(maiorFilhoAEsquerdaDoNodoDesbalanceado);

        nodoDesbalanceado.setAltura(Math.max(getAlturaArvore(nodoDesbalanceado.getEsquerda()), getAlturaArvore(nodoDesbalanceado.getDireita())) + 1);
        nodoDesbalanceadoEsquerda.setAltura(Math.max(getAlturaArvore(nodoDesbalanceadoEsquerda.getEsquerda()), getAlturaArvore(nodoDesbalanceadoEsquerda.getDireita())) + 1);

        return nodoDesbalanceadoEsquerda;
    }

    /**
     * Rotaciona à esquerda
     * @param nodoDesbalanceado Nodo desbalanceado
     * @return Retorna o nodo rotacionado para o lugar do nodo desbalanceado
     */
    private Nodo rotacaoAEsquerda(Nodo nodoDesbalanceado) {
        Nodo nodoDesbalanceadoDireita = nodoDesbalanceado.getDireita();
        Nodo menorFilhoADireitaDoNodoDesbalanceado = nodoDesbalanceadoDireita.getEsquerda();

        nodoDesbalanceadoDireita.setEsquerda(nodoDesbalanceado);
        nodoDesbalanceado.setDireita(menorFilhoADireitaDoNodoDesbalanceado);

        nodoDesbalanceado.setAltura(Math.max(getAlturaArvore(nodoDesbalanceado.getEsquerda()), getAlturaArvore(nodoDesbalanceado.getDireita())) + 1);
        nodoDesbalanceadoDireita.setAltura(Math.max(getAlturaArvore(nodoDesbalanceadoDireita.getEsquerda()), getAlturaArvore(nodoDesbalanceadoDireita.getDireita())) + 1);

        return nodoDesbalanceadoDireita;
    }

}
