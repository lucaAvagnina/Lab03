package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	private List<String> paroleDizionario = new LinkedList<String>();
	private int contatore;
	
	public void loadDictionary(String language) {
		
	try {
		FileReader fr = new FileReader( "rsc/"+language+".txt" );
		BufferedReader br = new BufferedReader(fr);
		String word ;
		while (( word = br .readLine()) != null ) {
			paroleDizionario.add(word);
		}
		br .close();
		} catch (IOException e ){
		System. out .println( "Errore nella lettura del file" );
		}
	}
	
		public List<RichWord> spellCheckText(List<String> inputTextList ){
			
			List<RichWord> richWordList = new LinkedList();
			
			for(String word : inputTextList) {
				RichWord rw = new RichWord();
				rw.setParolaInserita(word);
				if(paroleDizionario.contains(word)) {
					rw.setParolaPresente(true);
				}
				richWordList.add(rw);
			}
			
			return richWordList;
			
		}
		
		public List<RichWord> spellCheckTextLinear(List<String> inputTextList ){
			List<RichWord> richWordList = new LinkedList();
			
			for(String word : inputTextList) {
				RichWord rw = new RichWord();
				rw.setParolaInserita(word);
				for(String s : paroleDizionario) {
					if(s.equals(word)) {
						rw.setParolaPresente(true);
						break;
					}
				}
				richWordList.add(rw);
			}
			
			return richWordList;
		}
		
		public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList ){
			List<RichWord> richWordList = new LinkedList();
			
			for(String word : inputTextList) {
				RichWord rw = new RichWord();
				rw.setParolaInserita(word);
				int centro = paroleDizionario.size()/2;
				int grandezzaVocabolario = paroleDizionario.size();
				int comparazione = rw.getParolaInserita().compareTo(paroleDizionario.get(centro));
				boolean trovato = false;
				
				if(comparazione == 0) {
					rw.setParolaPresente(true);
				}
				
				if(comparazione < 0) {
					for(int i = centro; i < grandezzaVocabolario && trovato == false; i++) {
						if(word.equals(paroleDizionario.get(i))) {
							rw.setParolaPresente(true);
							trovato = true;
						}
					}
				}
				
				if(comparazione > 0) {
					for(int i = centro; i > -1 || trovato == false ; i--) {
						if(word.equals(paroleDizionario.get(i))) {
							rw.setParolaPresente(true);
							trovato=true;
						}
					}
				}
				
				richWordList.add(rw);
			}
			
			return richWordList;
			
		}

		public String paroleErrate(List<RichWord> listaRichWord) {
			
			String paroleErrate = "";
			contatore = 0;
			
			for(RichWord rw : listaRichWord) {
				if(rw.isParolaPresente() != true) {
					contatore++;
					paroleErrate += rw.getParolaInserita()+"\n";
				}
			}
			
			return paroleErrate.trim();
		}

		public String getContatore() {
			return Integer.toString(contatore);
		}

		
		
}
