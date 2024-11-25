package com.mycompany.n2_viotti;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.Cursor;

public class SenaryController {
    @FXML
    private Button btn6_Adicionar;

    @FXML
    private Button btn6_MaisOpcoes;

    @FXML
    private Button btn6_Comanda;

    @FXML
    private ImageView img6_Agua;

    @FXML
    private ImageView img6_Budweiser;

    @FXML
    private ImageView img6_C;

    @FXML
    private ImageView img6_Corona;

    @FXML
    private ImageView img6_Heineken;

    @FXML
    private ImageView img6_Java;

    @FXML
    private ImageView img6_Python;

    @FXML
    private ImageView img6_Redbull;

    @FXML
    private ImageView img6_Refrigerante;

    @FXML
    private ImageView img6_VBA;

    @FXML
    private TextField txt6_Comanda;

    @FXML
    private TextField txt6_Produto;

    @FXML
    private TextField txt6_Quantidade;

    @FXML
    private TextField txt6_Total;

    @FXML
    private Button btn6_Voltar;

    public void initialize() {
        // Configurar o evento de clique para o botão de voltar
        btn6_Voltar.setOnAction(event -> {
            try {
                voltarParaPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de comanda
        btn6_Comanda.setOnAction(event -> {
            try {
                irParaQuinary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de mais opções
        btn6_MaisOpcoes.setOnAction(event -> {
            try {
                irParaSeptenary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Definir o cursor de mão para os botões
        setHandCursor(btn6_Adicionar);
        setHandCursor(btn6_MaisOpcoes);
        setHandCursor(btn6_Comanda);
        setHandCursor(btn6_Voltar);

        // Definir o cursor de mão para as imagens
        setHandCursor(img6_Agua);
        setHandCursor(img6_Budweiser);
        setHandCursor(img6_C);
        setHandCursor(img6_Corona);
        setHandCursor(img6_Heineken);
        setHandCursor(img6_Java);
        setHandCursor(img6_Python);
        setHandCursor(img6_Redbull);
        setHandCursor(img6_Refrigerante);
        setHandCursor(img6_VBA);
    }

    // Método para voltar para o primary.fxml
    private void voltarParaPrimary() throws IOException {
        Parent primaryRoot = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Stage stage = (Stage) btn6_Voltar.getScene().getWindow();
        stage.setScene(new Scene(primaryRoot));
    }

    // Método para ir para o quinary.fxml
    private void irParaQuinary() throws IOException {
        Parent quinaryRoot = FXMLLoader.load(getClass().getResource("quinary.fxml"));
        Stage stage = (Stage) btn6_Comanda.getScene().getWindow();
        stage.setScene(new Scene(quinaryRoot));
    }

    // Método para ir para o septenary.fxml
    private void irParaSeptenary() throws IOException {
        Parent septenaryRoot = FXMLLoader.load(getClass().getResource("septenary.fxml"));
        Stage stage = (Stage) btn6_MaisOpcoes.getScene().getWindow();
        stage.setScene(new Scene(septenaryRoot));
    }

    // Método para definir o cursor de mão para um botão ou ImageView
    private void setHandCursor(javafx.scene.Node node) {
        node.setCursor(Cursor.HAND);
    }
}
