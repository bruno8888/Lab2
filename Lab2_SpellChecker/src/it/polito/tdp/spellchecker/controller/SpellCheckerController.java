package it.polito.tdp.spellchecker.controller;

	
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

	public class SpellCheckerController {
		
		Dictionary model;
		String lingua;
		List<String>frase=new ArrayList<String>();
		
		public void setmodel(Dictionary model){
			this.model=model;
		}

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<String> cbLingua;

	    @FXML
	    private TextArea txtTesto;

	    @FXML
	    private Button btnSpell;

	    @FXML
	    private TextFlow txtResult;

	    @FXML
	    private Label lblMsg;

	    @FXML
	    private Button btnClear;

	    @FXML
	    private Label lblTime;

	    @FXML
	    void doClear(ActionEvent event) {
	    	txtResult.getChildren().clear();;
            
	    	txtTesto.setText("");
	    	lblTime.setText("");
	    	lblMsg.setText("");
	    }

	    @FXML
	    void doSpellCheck(ActionEvent event) {

	    	lingua=cbLingua.getValue();
	    	boolean errori=false;
	    	if (lingua.compareTo("English")==0){
	    		model=new EnglishDictionary();
	    		model.loadDictionary();
	    		String parole[]=txtTesto.getText().toLowerCase().split(" ");
	    		for(int i=0;i<parole.length;i++){
	    			if (parole[i].contains(",") || parole[i].contains("."))
	    				parole[i]=parole[i].substring(0, parole[i].length()-1);
	    			frase.add(parole[i]);
	    		}
	    		long t0=System.currentTimeMillis();
	    		List<RichWord>lista=model.spellCheckText(frase);
	    		long t=(System.currentTimeMillis()-t0);
	    		lblTime.setText("tempo per il controllo: "+t+" millisecondi");
	    		
	    		for(RichWord r:lista){
	    			Text testo=new Text(r.getParola()+" ") ;
	    			if(r.isErrata()==true){
	    				
	    				errori=true;
	    				testo.setFill(Color.RED);
	    			}
	    			txtResult.getChildren().add(testo);
	    		}
	    		if (errori==true){
	    			
	    			lblMsg.setText("il testo ha errori");
	    		}
	    		else
	    			lblMsg.setText("nessun errore");
	    		frase.clear();
	    		
	    	}
	    	if (lingua.compareTo("Italian")==0){
	    		model=new ItalianDictionary();
	    		model.loadDictionary();
	    		String parole[]=txtTesto.getText().toLowerCase().split(" ");
	    		for(int i=0;i<parole.length;i++){
	    			if (parole[i].contains(",") || parole[i].contains("."))
	    				parole[i]=parole[i].substring(0, parole[i].length()-1);
	    			frase.add(parole[i]);
	    		}
	    		long t0=System.currentTimeMillis();
	    		List<RichWord>lista=model.spellCheckText(frase);
	    		long t=(System.currentTimeMillis()-t0);
	    		lblTime.setText("tempo per il controllo: "+t+" millisecondi");
	    		
	    		for(RichWord r:lista){
	    			Text testo=new Text(r.getParola()+" ") ;
	    			if(r.isErrata()==true){
	    				
	    				errori=true;
	    				testo.setFill(Color.RED);
	    			}
	    			txtResult.getChildren().add(testo);
	    		}
	    		if (errori==true){
	    			
	    			lblMsg.setText("il testo ha errori");
	    		}
	    		else
	    			lblMsg.setText("nessun errore");
	    		frase.clear();
	    		
	    	}
	    	
	    }

	    @FXML
	    void initialize() {
	        assert cbLingua != null : "fx:id=\"cbLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert lblMsg != null : "fx:id=\"lblMsg\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        
	        cbLingua.getItems().addAll("English","Italian");
	        lblTime.setText("");
	        lblMsg.setText("Scegli una lingua");

	    }
	}