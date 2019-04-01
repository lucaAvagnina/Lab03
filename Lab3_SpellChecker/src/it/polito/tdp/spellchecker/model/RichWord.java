package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String parolaInserita;
	private boolean parolaPresente;

	public RichWord() {
		parolaPresente = false;
	}
	
	public String getParolaInserita() {
		return parolaInserita;
	}
	public void setParolaInserita(String parolaInserita) {
		this.parolaInserita = parolaInserita;
	}
	public boolean isParolaPresente() {
		return parolaPresente;
	}
	public void setParolaPresente(boolean parolaPresente) {
		this.parolaPresente = parolaPresente;
	}
	
}
