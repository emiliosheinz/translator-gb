package br.com.unisinos.tranlatorgb;

import java.util.List;

public class Dicionario {

    protected String palavra;
    protected List<String> definicoes;

    public Dicionario(String palavra, List<String> definicoes) {
        this.palavra = palavra;
        this.definicoes = definicoes;
    }

}
