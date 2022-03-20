package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.alien.model.Dizionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FXMLController {
	private Dizionario model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtDaTradurre;

    @FXML
    private TextArea txtTraduzione;
    
    @FXML
    private Label txtErrore;

    @FXML
    void handleClear(ActionEvent event) {
        txtErrore.setText("");
        txtDaTradurre.clear();
        txtTraduzione.clear();
    }

    @FXML
    void handleTraduci(ActionEvent event) {
    	
    	txtErrore.setText("");
    	String[] input = txtDaTradurre.getText().split(" ");
    	if(input.length>1) {
    		if(!input[0].matches("[a-zA-z]*")) {
        		txtErrore.setText("ERRORE: sono presenti caratteri proibiti");
        		return;
        	} 
    		if(!input[1].matches("[a-zA-z]*")) {
        		txtErrore.setText("ERRORE: sono presenti caratteri proibiti");
        		return;
        	} 
    	model.addTraduzione(input[0],input[1]);
    	}
    	else {
    	if(!input[0].matches("[a-zA-z]*[?]{0,1}[a-zA-z]*")) {
    		txtErrore.setText("ERRORE: sono presenti caratteri proibiti");
    		return;
    	} 
    	
    	if(model.isAmbiguo(input[0])) {
    		txtErrore.setText("Alla parola inserita sono associabili più parole. Non è possibile identificare univocamente la parola cercata e fornire la traduzione");
    	}
    	if(model.cercaTraduzioni(input[0])==null) {
    		txtErrore.setText("Non è disponibile traduzione per questa parola");
    	}
    	txtTraduzione.setText("traduzioni di "+input[0]+" :"+model.stampaVoci(model.cercaTraduzioni(input[0])));
    		
    	}
       
    }
    
    public void setModel(Dizionario model) {
    	this.model=model;
    }


    @FXML
    void initialize() {
        assert txtDaTradurre != null : "fx:id=\"txtDaTradurre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTraduzione != null : "fx:id=\"txtTraduzione\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}

