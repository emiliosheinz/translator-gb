package br.com.unisinos.tranlatorgb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
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
