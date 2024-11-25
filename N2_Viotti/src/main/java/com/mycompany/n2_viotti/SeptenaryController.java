package com.mycompany.n2_viotti;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;

public class SeptenaryController {
    @FXML
    private Button btn7_Adicionar;

    @FXML
    private Button btn7_Comanda;

    @FXML
    private Button btn7_Mais;

    @FXML
    private Button btn7_Voltar;

    @FXML
    private ComboBox<?> cmb7_Produto;

    @FXML
    private ComboBox<?> cmb7_TipoDeBebida;

    @FXML
    private TextField txt7_Comanda;

    @FXML
    private TextField txt7_Descricao;

    @FXML
    private TextField txt7_Produto;

    @FXML
    private TextField txt7_Quantidade;

    @FXML
    private TextField txt7_Total;

    @FXML
    private TextField txt7_Valor;

    // Método de inicialização
    public void initialize() {
        // Configurar o evento de clique para o botão de voltar
        btn7_Voltar.setOnAction(event -> {
            try {
                voltarParaPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de comanda
        btn7_Comanda.setOnAction(event -> {
            try {
                irParaQuinary(); // Navega para a quinary.fxml
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Definir o cursor de mão para os botões
        setHandCursor(btn7_Adicionar);
        setHandCursor(btn7_Mais);
        setHandCursor(btn7_Comanda);
        setHandCursor(btn7_Voltar);
    }

    // Método para voltar para o primary.fxml
    private void voltarParaPrimary() throws IOException {
        // Carregar o arquivo primary.fxml
        Parent primaryRoot = FXMLLoader.load(getClass().getResource("primary.fxml"));
        
        // Obter o stage atual e definir a nova cena
        Stage stage = (Stage) btn7_Voltar.getScene().getWindow();
        stage.setScene(new Scene(primaryRoot));
    }

    // Método para ir para o quinary.fxml
    private void irParaQuinary() throws IOException {
        // Carregar o arquivo quinary.fxml
        Parent quinaryRoot = FXMLLoader.load(getClass().getResource("quinary.fxml"));

        // Obter o stage atual e definir a nova cena
        Stage stage = (Stage) btn7_Comanda.getScene().getWindow();
        stage.setScene(new Scene(quinaryRoot));
    }

    // Método para definir o cursor de mão para um botão ou ImageView
    private void setHandCursor(javafx.scene.Node node) {
        node.setCursor(Cursor.HAND);
    }
}