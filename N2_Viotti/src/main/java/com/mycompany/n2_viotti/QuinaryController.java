package com.mycompany.n2_viotti;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Cursor;
import javafx.stage.Stage;

public class QuinaryController {

    @FXML
    private Button btn5_PagarCSS;

    @FXML
    private Button btn5_Pesquisar;

    @FXML
    private Button btn5_Voltar;

    @FXML
    private ComboBox<?> cmb5_FormasPagamento;

    @FXML
    private TableColumn<?, ?> tblView5_Cancelar;

    @FXML
    private TableView<?> tblView5_Comanda;

    @FXML
    private TableColumn<?, ?> tblView5_Horario;

    @FXML
    private TableColumn<?, ?> tblView5_Produto;

    @FXML
    private TextField txt5_Comanda;

    @FXML
    private TextField txt5_Total;

    @FXML
    private TextField txt5_Troco;

    @FXML
    private TextField txt5_ValorRecebido;

    public void initialize() {
        
        // Ação do botão Voltar
        btn5_Voltar.setOnAction(event -> {
            try {
                voltarParaPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configurar o cursor como mão nos botões
        setHandCursor(btn5_PagarCSS);
        setHandCursor(btn5_Pesquisar);
        setHandCursor(btn5_Voltar);

    }

    private void voltarParaPrimary() throws IOException {
        // Carregar a tela primária
        Parent primaryRoot = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Stage stage = (Stage) btn5_Voltar.getScene().getWindow();
        stage.setScene(new Scene(primaryRoot));
    }

    private void setHandCursor(Button button) {
        // Define o cursor de mão para os botões
        button.setCursor(Cursor.HAND);
    }
}