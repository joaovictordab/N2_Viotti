package com.mycompany.n2_viotti;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.bean.Bebidas;
import model.dao.BebidasDAO;
import javafx.scene.control.TextField;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SecondaryController {

    @FXML
    private ComboBox<String> cmb2_TipoBebida;

    @FXML
    private ComboBox<String> cmb2_Alcoolica;

    @FXML
    private TextField txt2_NomeBebida;

    @FXML
    private TextField txt2_DescricaoBebida;

    @FXML
    private TextField txt2_PrecoBebida;

    @FXML
    private TextField txt2_QuantidadeMLBebida;

    @FXML
    private TextField txt2_CodigoBebida;

    @FXML
    private Button btn2_Pesquisar;

    @FXML
    private Button btn2_Excluir;

    @FXML
    private Button btn2_Editar;

    @FXML
    private Button btn2_Salvar;

    @FXML
    private Button btn2_Voltar;

    private final Set<Integer> numerosGerados = new HashSet<>();

    public void initialize() {

        // Adicionar valores fixos aos ComboBox
        cmb2_TipoBebida.getItems().addAll(
                "Drinks autorias",
                "Softs",
                "doses",
                "Cervejas",
                "Combos",
                "Drinks clássicos",
                "Drinks sem álcool",
                "Garrafas",
                "Energéticos");

        cmb2_Alcoolica.getItems().addAll(
                "Sim",
                "Não");

        // Configurar o evento de clique para o botão de voltar
        btn2_Voltar.setOnAction(event -> {
            try {
                voltarParaPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Definir o cursor de mão para os botões
        setHandCursor(btn2_Pesquisar);
        setHandCursor(btn2_Excluir);
        setHandCursor(btn2_Editar);
        setHandCursor(btn2_Salvar);
        setHandCursor(btn2_Voltar);

        btn2_Salvar.setOnAction(event -> salvar());
    }

    // Método para salvar os dados
    private void salvar() {
        if (!camposVazios2()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor, preencha todos os campos.");
            alert.setContentText("Certifique-se de que todos os campos estejam preenchidos antes de salvar.");
            alert.showAndWait();
            return;

        } else {
            // Gerar código único para a bebida
            Random random = new Random();
            int codigoBebida;
            do {
                codigoBebida = 1000 + random.nextInt(9000); // Gera um número entre 1000 e 9999
            } while (numerosGerados.contains(codigoBebida));
            numerosGerados.add(codigoBebida);
            txt2_CodigoBebida.setText(String.valueOf(codigoBebida));

            // Criar objeto Bebidas
            Bebidas bebida = new Bebidas();
            bebida.setCodigoBebida(codigoBebida); // Certifique-se de que o atributo 'codigo' exista na classe Bebidas
            bebida.setNome(txt2_NomeBebida.getText());
            bebida.setDescricao(txt2_DescricaoBebida.getText());
            bebida.setPreco(Double.parseDouble(txt2_PrecoBebida.getText()));
            bebida.setQuantidadeML(Integer.parseInt(txt2_QuantidadeMLBebida.getText()));
            bebida.setTipo(cmb2_TipoBebida.getValue());
            bebida.setAlcoolica(cmb2_Alcoolica.getValue().equals("Sim"));
            BebidasDAO bebidasDAO = new BebidasDAO();
            boolean sucesso = bebidasDAO.salvar(bebida);

            if (sucesso) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Dados salvos com sucesso!");
                alert.setContentText("A bebida foi cadastrada no banco de dados.");
                alert.showAndWait();
                limparCampos();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro ao salvar os dados");
                alert.setContentText("Houve um erro ao salvar os dados no banco de dados. Tente novamente.");
                alert.showAndWait();
            }
        }
    }

    // ------------------------------------------------------------------------------------------------//

    // Método para voltar para o primary.fxml
    private void voltarParaPrimary() throws IOException {
        Parent primaryRoot = FXMLLoader.load(getClass().getResource("primary.fxml"));

        Stage stage = (Stage) btn2_Voltar.getScene().getWindow();
        stage.setScene(new Scene(primaryRoot));
    }

    // Método para definir o cursor de mão para um botão
    private void setHandCursor(Button button) {
        button.setCursor(Cursor.HAND);
    }

    // método limpar campos
    private void limparCampos() {
        txt2_NomeBebida.clear();
        txt2_DescricaoBebida.clear();
        txt2_PrecoBebida.clear();
        txt2_QuantidadeMLBebida.clear();
        txt2_CodigoBebida.clear();
    }

    // Método para verificar se todos os campos obrigatórios estão preenchidos
    private boolean camposVazios2() {
        return !(cmb2_TipoBebida.getValue() == null ||
                cmb2_Alcoolica.getValue() == null ||
                txt2_NomeBebida.getText().isEmpty() ||
                txt2_DescricaoBebida.getText().isEmpty() ||
                txt2_PrecoBebida.getText().isEmpty() ||
                txt2_QuantidadeMLBebida.getText().isEmpty());
    }
}