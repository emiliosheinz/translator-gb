package br.com.unisinos.tranlatorgb;

import java.util.ArrayList;
import java.util.List;

public class TradutorMain {

	public static void main(String[] args) {
		
		Tradutor tradutor = new Tradutor();
		
		List<String> definicoes = new ArrayList<>();
		definicoes.add("bola");
		definicoes.add("bolita");
		definicoes.add("pelota");
		
		tradutor.insereTraducao("ball", definicoes);
	}

}