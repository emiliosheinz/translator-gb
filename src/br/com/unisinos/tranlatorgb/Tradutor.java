package br.com.unisinos.tranlatorgb;

import br.com.unisinos.tranlatorgb.arvore.ArvoreAVL;
import br.com.unisinos.tranlatorgb.arvore.Nodo;
import br.com.unisinos.tranlatorgb.enums.OrdemDeLeituraArvore;
import br.com.unisinos.tranlatorgb.exceptions.NodoInvalidoException;
import br.com.unisinos.tranlatorgb.exceptions.PalavraNaoEncontradaException;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Tradutor {

    ArvoreAVL arvoreAVL;

    protected ArvoreAVL carregaDicionario(String arq) {
        arvoreAVL = null;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

            Dicionario dicionario = null;
            String linha;

            while ((linha = br.readLine()) != null) {

                List<String> traducoes = new ArrayList<>();
                String[] palavras = linha.split("#");
                String chave = palavras[0];

                for (int i = 1; i < palavras.length; i++) {
                    traducoes.add(palavras[i]);
                }

                dicionario = new Dicionario(chave, traducoes);
                Nodo novoNodo = new Nodo(dicionario);

                if (count == 0) {
                    arvoreAVL = new ArvoreAVL(novoNodo);
                    count++;
                } else {
                    try {
                        count++;
                        arvoreAVL.insereNodoComVerificacao(novoNodo, arvoreAVL.getRaiz());
                    } catch (NodoInvalidoException nie) {
                        System.out.println(nie.getMensagem());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arvoreAVL;
    }

    public List<String> traduzPalavra(String palavra) throws PalavraNaoEncontradaException {
        Nodo nodo = arvoreAVL.pesquisaValor(palavra, arvoreAVL.getRaiz());
        if (Objects.isNull(nodo)) {
            throw new PalavraNaoEncontradaException("A palavra '" + palavra + "' não está no nosso dicionário.");
        }

        return nodo.getChave().getDefinicoes();
    }

    public ArvoreAVL insereTraducao(String palavra, List<String> definicoes) {

        Dicionario chave = new Dicionario(palavra, definicoes);
        Nodo nodo = new Nodo(chave);

        try {
            arvoreAVL.insereNodoComVerificacao(nodo, arvoreAVL.getRaiz());
        } catch (NodoInvalidoException nie) {
            System.out.println(nie.getMensagem());
        }

        return arvoreAVL;

    }

    public void salvaDicionario(String arq, OrdemDeLeituraArvore ordem) {

        List<Dicionario> lista = new LinkedList<>();

        if(ordem == OrdemDeLeituraArvore.EM_ORDERM){
            lista = arvoreAVL.emOrdem(arvoreAVL.getRaiz(), new LinkedList<>());
        } else if(ordem == OrdemDeLeituraArvore.POS_ORDEM){
            lista = arvoreAVL.posOrdem(arvoreAVL.getRaiz(), new LinkedList<>());
        }else if(ordem == OrdemDeLeituraArvore.PRE_ORDERM){
            lista = arvoreAVL.preOrdem(arvoreAVL.getRaiz(), new LinkedList<>());
        }

        for (int i = 0; i < lista.size(); i++) {
            Dicionario dicionario = lista.get(i);
            boolean append = i == 0;

            escreveNoArquivo(dicionario.palavra, dicionario.getDefinicoes(), !append);
        }

    }

    private void escreveNoArquivo(String palavra, List<String> definicoes, boolean append) {

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

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("dicionario.dat", append));

            if (new File("dicionario.dat").length() != 0) {
                writer.newLine();
            }
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
