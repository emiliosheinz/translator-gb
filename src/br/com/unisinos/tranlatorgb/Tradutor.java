package br.com.unisinos.tranlatorgb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Tradutor {

    protected void carregaDicionario(String arq){
        try (BufferedReader br = new BufferedReader(new FileReader("dicionario.dat"))) {

            Dicionario dicionario;
            String linha, chave = "";
            List<String> traducoes = Collections.emptyList();

            while ((linha = br.readLine()) != null) {

                String [] palavras = linha.split("#");

                for (int i = 0; i < palavras.length; i++) {
                    if(i == 0) {
                        chave = palavras[i];
                    }else {
                        traducoes.add(palavras[i]);
                    }
                }

                dicionario = new Dicionario(chave, traducoes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List traduzPalavra(String palavra){
        return Collections.emptyList();
    }

    public void insereTraducao(String palavra, List definicoes) {}

    public void salvaDicionario(String arq) {}

}
