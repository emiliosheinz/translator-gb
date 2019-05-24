package br.com.unisinos.tranlatorgb;

import br.com.unisinos.tranlatorgb.enums.OrdemDeLeituraArvore;
import br.com.unisinos.tranlatorgb.exceptions.PalavraNaoEncontradaException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradutorMain {

    public static void main(String[] args) {

        Tradutor tradutor = new Tradutor();

        tradutor.carregaDicionario("dicionario.dat");

        List<String> traducoesSwitch= new ArrayList<>();
        traducoesSwitch.add("trocar");
        traducoesSwitch.add("ecolher");

        List<String> novasTraducoes = new ArrayList<>();
        novasTraducoes.add("video-game");
        novasTraducoes.add("trocar");

        tradutor.insereTraducao("switch", traducoesSwitch);
        tradutor.insereTraducao("switch", novasTraducoes);
        try{
            List<String> traducoes = tradutor.traduzPalavra("switch");
            tradutor.traduzPalavra("xalala");
            System.out.print("breakpoint");
        }catch(PalavraNaoEncontradaException pne) {
            System.out.println(pne.getMensagem());
        }

        tradutor.salvaDicionario("dicionario.dat", OrdemDeLeituraArvore.EM_ORDERM);

        System.out.println("breakpoint");
    }
}
