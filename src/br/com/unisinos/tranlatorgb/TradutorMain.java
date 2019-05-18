package br.com.unisinos.tranlatorgb;

import br.com.unisinos.tranlatorgb.arvore.ArvoreAVL;

import java.util.LinkedList;
import java.util.List;

public class TradutorMain {

    public static void main(String[] args) {

        Tradutor tradutor = new Tradutor();

        ArvoreAVL arvore = tradutor.carregaDicionario("dicionario.dat");

        List<String> traducoes = new LinkedList<>();
        traducoes.add("testee");
        traducoes.add("bola");

        arvore = tradutor.insereTraducao("ball", traducoes);

        System.out.println(arvore);
    }

}
