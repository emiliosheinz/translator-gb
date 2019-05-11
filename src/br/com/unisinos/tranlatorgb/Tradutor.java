package br.com.unisinos.tranlatorgb;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tradutor {

    protected void carregaDicionario(String arq) {
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

            Dicionario dicionario;
            String linha;
            List<Dicionario> palavrasDicionario = new ArrayList<>();

            while ((linha = br.readLine()) != null) {

                List<String> traducoes = new ArrayList<>();
                String[] palavras = linha.split("#");
                String chave = palavras[0];

                for (int i = 1; i < palavras.length; i++) {
                    traducoes.add(palavras[i]);
                }

                dicionario = new Dicionario(chave, traducoes);
                palavrasDicionario.add(dicionario);
            }

            System.out.println(palavrasDicionario);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> traduzPalavra(String palavra) {
        return Collections.emptyList();
    }

    public void insereTraducao(String palavra, List<String> definicoes) {
        StringBuilder builder = new StringBuilder();

        builder.append(palavra + "#");
        for (int i = 0; i < definicoes.size(); i++) {
            if (i == definicoes.size() - 1) {
                builder.append(definicoes.get(i));
            } else {
                builder.append(definicoes.get(i) + "#");
            }
        }

        File file = new File("dicionario.dat");
        FileWriter fr = null;

        try {
            fr = new FileWriter(file);
            fr.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void salvaDicionario(String arq) {
    }

}
