package br.com.unisinos.tranlatorgb;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tradutor {

    protected void carregaDicionario(String arq) {
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

            Dicionario dicionario;
            String linha, chave = "";
            List<String> traducoes = new ArrayList<>();

            while ((linha = br.readLine()) != null) {

                String[] palavras = linha.split("#");

                for (int i = 0; i < palavras.length; i++) {
                    if (i == 0) {
                        chave = palavras[i];
                    } else {
                        traducoes.add(palavras[i]);
                    }
                }

                dicionario = new Dicionario(chave, traducoes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> traduzPalavra(String palavra) {
        return Collections.emptyList();
    }

    public void insereTraducao(String palavra, List<String> definicoes) {

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
            writer = new BufferedWriter(new FileWriter("dicionario.dat", true));

            if (new File("dicionario.dat").length() != 0) {
                writer.newLine();
            }
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void salvaDicionario(String arq) {
    }

}
