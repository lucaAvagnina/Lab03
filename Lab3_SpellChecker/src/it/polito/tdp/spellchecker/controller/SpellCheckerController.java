package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> mnLanguage;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnClearText;

    @FXML
    private Label lblErrors;

    @FXML
    private Label lblTime;
    
    

    @FXML
    void doClearText(ActionEvent event) {
    	
    	txtInput.clear();
    	txtOutput.clear();
    	lblTime.setText(null);;
    	lblErrors.setText("The text contains 0 errors");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	long start = System.nanoTime();
    	
    	List <String>listaParoleInput = new LinkedList<String>(); 
    	List <RichWord>listaRichWord =new LinkedList<RichWord>();
    	
    	String language = mnLanguage.getValue();
    	model.loadDictionary(language);
    	
    	String dato[] = txtInput.getText().split(" ");
    	
    	for(int i = 0; i<dato.length; i++) {
    		String parola;
    		parola = dato[i].replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    		listaParoleInput.add(parola); 
    	}
    	
    	listaRichWord.addAll(model.spellCheckText(listaParoleInput));
    	txtOutput.appendText(model.paroleErrate(listaRichWord));
    	lblErrors.setText("The text contains " + model.getContatore() + " errors");
    	
    	lblTime.setText("Spell check completed in "+ (Double.toString((System.nanoTime() - start)*1e-9)) + " sec");
    }

    @FXML
    void initialize() {
        assert mnLanguage != null : "fx:id=\"mnLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    void setModel(Dictionary model) {
    	this.model = model;
    	mnLanguage.getItems().addAll("Italian","English");
    }
}
