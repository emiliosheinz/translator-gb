package br.com.unisinos.tranlatorgb;

import br.com.unisinos.tranlatorgb.arvore.ArvoreAVL;
import br.com.unisinos.tranlatorgb.arvore.Nodo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TradutorMain {

    public static void main(String[] args) {

        Tradutor tradutor = new Tradutor();

        ArvoreAVL arvore = tradutor.carregaDicionario("dicionario.dat");

        List<Dicionario> lista = arvore.preOrdem(arvore.getRaiz(), new LinkedList<>());
        List<Dicionario> lista2 = arvore.emOrdem(arvore.getRaiz(), new LinkedList<>());
        List<Dicionario> lista3 = arvore.posOrdem(arvore.getRaiz(), new LinkedList<>());

        System.out.println(lista);

//		List<String> definicoes = new ArrayList<>();
//		definicoes.add("bola");
//		definicoes.add("bolita");
//		definicoes.add("pelota");
//
//		tradutor.insereTraducao("ball", definicoes);
    }

}
