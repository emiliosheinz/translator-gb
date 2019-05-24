package br.com.unisinos.tranlatorgb;

import br.com.unisinos.tranlatorgb.arvore.ArvoreAVL;
import br.com.unisinos.tranlatorgb.arvore.Nodo;
import br.com.unisinos.tranlatorgb.enums.OrdemDeLeituraArvore;
import br.com.unisinos.tranlatorgb.exceptions.ChaveInvalidaException;
import br.com.unisinos.tranlatorgb.exceptions.PalavraNaoEncontradaException;

import java.io.*;
import java.util.*;

public class Tradutor {

    private ArvoreAVL arvoreAVL;

    /**
     * Lê o arquivo e mapeia para uma árvore AVL
     * @param arq Nome do arquivo
     */
    protected void carregaDicionario(String arq) {
        arvoreAVL = null;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

            Dicionario dicionario;
            String linha;

            while ((linha = br.readLine()) != null) {

                String[] palavras = linha.split("#");
                String chave = palavras[0];

                List<String> traducoes = new ArrayList<>(Arrays.asList(palavras).subList(1, palavras.length));

                dicionario = new Dicionario(chave, traducoes);
                Nodo novoNodo = new Nodo(dicionario);

                if (count == 0) {
                    arvoreAVL = new ArvoreAVL(novoNodo);
                    count++;
                } else {
                    try {
                        count++;
                        arvoreAVL.setRaiz(arvoreAVL.insereNodoComVerificacao(novoNodo.getChave(), arvoreAVL.getRaiz()));
                    } catch (ChaveInvalidaException e) {
                        System.out.println(e.getMensagem());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pesquisa palavra recebida por parâmetro na AVL, ao encontrar, retorna lista de definições,
     * ao não encontrar, lança uma exception.
     * @param palavra Palavra a ser procurada na AVL
     * @return Definições da palavra encontrada
     * @throws PalavraNaoEncontradaException Exceção lançada quando não é encontrada a palavra desejada
     */
    public List<String> traduzPalavra(String palavra) throws PalavraNaoEncontradaException {
        Nodo nodo = arvoreAVL.pesquisaValor(palavra, arvoreAVL.getRaiz());
        if (Objects.isNull(nodo)) {
            throw new PalavraNaoEncontradaException("A palavra '" + palavra + "' não está no nosso dicionário.");
        }

        return nodo.getChave().getDefinicoes();
    }

    /**
     * Insere uma nova tradução para a palavra caso ela já exista.
     * Se não existir, insere nova palavra e definições
     * @param palavra Palavra a qual deve ser inserida a tradução
     * @param definicoes Definições a serem inseridas
     */
    public void insereTraducao(String palavra, List<String> definicoes) {

        Dicionario chave = new Dicionario(palavra, definicoes);

        try {
            arvoreAVL.insereNodoComVerificacao(chave, arvoreAVL.getRaiz());
        } catch (ChaveInvalidaException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Salva o dicionário (árvore AVL) no arquivo passado por parâmetro na ordem também passada por parâmetro.
     * @param arq Nome do arquivo a ser escrito
     * @param ordem Ordem em que deve ser escrita as palavras no arquivo
     */
    public void salvaDicionario(String arq, OrdemDeLeituraArvore ordem) {

        List<Dicionario> lista = new LinkedList<>();

        if (ordem == OrdemDeLeituraArvore.EM_ORDERM) {
            lista = arvoreAVL.emOrdem(arvoreAVL.getRaiz(), new LinkedList<>());
        } else if (ordem == OrdemDeLeituraArvore.POS_ORDEM) {
            lista = arvoreAVL.posOrdem(arvoreAVL.getRaiz(), new LinkedList<>());
        } else if (ordem == OrdemDeLeituraArvore.PRE_ORDERM) {
            lista = arvoreAVL.preOrdem(arvoreAVL.getRaiz(), new LinkedList<>());
        }

        for (int i = 0; i < lista.size(); i++) {
            Dicionario dicionario = lista.get(i);
            boolean append = i == 0;

            escreveNoArquivo(arq, dicionario.palavra, dicionario.getDefinicoes(), !append);
        }

    }

    /**
     * Escreve no arquivo a palavra e definições passadas por parâmetro
     * @param arq Nome do arquivo
     * @param palavra Palavra a ser inserida
     * @param definicoes Lista de definições a serem inseridas
     * @param append Booleano que decide se deve sobreesrever ou não o arquivo
     */
    private void escreveNoArquivo(String arq, String palavra, List<String> definicoes, boolean append) {

        StringBuilder builder = new StringBuilder();

        builder.append(palavra)
                .append("#");

        for (int i = 0; i < definicoes.size(); i++) {
            if (i == definicoes.size() - 1) {
                builder.append(definicoes.get(i));
            } else {
                builder.append(definicoes.get(i))
                        .append("#");
            }
        }

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(arq, append));

            if (new File(arq).length() != 0) {
                writer.newLine();
            }
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
