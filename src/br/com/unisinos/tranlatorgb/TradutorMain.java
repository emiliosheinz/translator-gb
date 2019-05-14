package br.com.unisinos.tranlatorgb;

import br.com.unisinos.tranlatorgb.arvore.ArvoreAVL;
import br.com.unisinos.tranlatorgb.arvore.Nodo;

import java.util.ArrayList;
import java.util.List;

public class TradutorMain {

    public static void main(String[] args) {

        Tradutor tradutor = new Tradutor();

        ArvoreAVL arvore = tradutor.carregaDicionario("dicionario.dat");

        System.out.println(arvore);

//		List<String> definicoes = new ArrayList<>();
//		definicoes.add("bola");
//		definicoes.add("bolita");
//		definicoes.add("pelota");
//
//		tradutor.insereTraducao("ball", definicoes);
    }

}
