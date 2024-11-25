package com.mycompany.n2_viotti;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Cursor;

public class PrimaryController {

    @FXML
    private Button btn_AbrirComanda;

    @FXML
    private Button btn_Cardapio;

    @FXML
    private Button btn_Cbebidas;

    @FXML
    private Button btn_Cclientes;

    @FXML
    private Button btn_GerenciarComanda;

    @FXML
    private Button btn_Encerrar;

    public void initialize() {
        // Configurar o evento de clique para o botão de encerrar
        btn_Encerrar.setOnAction(event -> fecharPrograma());

        // Configurar o evento de clique para o botão de bebidas
        btn_Cbebidas.setOnAction(event -> {
            try {
                mudarParaSecondary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de clientes
        btn_Cclientes.setOnAction(event -> {
            try {
                mudarParaTertiary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de abrir comanda
        btn_AbrirComanda.setOnAction(event -> {
            try {
                mudarParaQuaternary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de gerenciar comanda
        btn_GerenciarComanda.setOnAction(event -> {
            try {
                mudarParaQuinary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o evento de clique para o botão de cardápio
        btn_Cardapio.setOnAction(event -> {
            try {
                mudarParaSenary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Definir o cursor de mão para os botões
        setHandCursor(btn_AbrirComanda);
        setHandCursor(btn_Cardapio);
        setHandCursor(btn_Cbebidas);
        setHandCursor(btn_Cclientes);
        setHandCursor(btn_GerenciarComanda);
        setHandCursor(btn_Encerrar);
    }

    // Método que fecha o programa
    private void fecharPrograma() {
        Stage stage = (Stage) btn_Encerrar.getScene().getWindow();
        stage.close();
    }

    // Método para mudar para o secondary.fxml
    private void mudarParaSecondary() throws IOException {
        Parent secondaryRoot = FXMLLoader.load(getClass().getResource("secondary.fxml"));
        Stage stage = (Stage) btn_Cbebidas.getScene().getWindow();
        stage.setScene(new Scene(secondaryRoot));
    }

    // Método para mudar para o tertiary.fxml
    private void mudarParaTertiary() throws IOException {
        Parent tertiaryRoot = FXMLLoader.load(getClass().getResource("tertiary.fxml"));
        Stage stage = (Stage) btn_Cclientes.getScene().getWindow();
        stage.setScene(new Scene(tertiaryRoot));
    }

    // Método para mudar para o quaternary.fxml
    private void mudarParaQuaternary() throws IOException {
        Parent quaternaryRoot = FXMLLoader.load(getClass().getResource("quaternary.fxml"));
        Stage stage = (Stage) btn_AbrirComanda.getScene().getWindow();
        stage.setScene(new Scene(quaternaryRoot));
    }

    // Método para mudar para o quinary.fxml
    private void mudarParaQuinary() throws IOException {
        Parent quinaryRoot = FXMLLoader.load(getClass().getResource("quinary.fxml"));
        Stage stage = (Stage) btn_GerenciarComanda.getScene().getWindow();
        stage.setScene(new Scene(quinaryRoot));
    }

    // Método para mudar para o senary.fxml
    private void mudarParaSenary() throws IOException {
        Parent senaryRoot = FXMLLoader.load(getClass().getResource("senary.fxml"));
        Stage stage = (Stage) btn_Cardapio.getScene().getWindow();
        stage.setScene(new Scene(senaryRoot));
    }

    // Método para definir o cursor de mão para um botão
    private void setHandCursor(Button button) {
        button.setCursor(Cursor.HAND);
    }
}