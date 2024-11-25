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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.bean.Cliente;
import model.dao.ClienteDAO;

public class QuaternaryController {

    @FXML
    private Button btn4_AbrirComanda;

    @FXML
    private Button btn4_GerarComanda;

    @FXML
    private Button btn4_Pesquisar;

    @FXML
    private Button btn4_Voltar;

    @FXML
    private Button btn4_GerenciarComanda; 

    @FXML
    private TextField txt4_Cpf;

    @FXML
    private TextField txt4_NomeCompleto;

    @FXML
    private TextField txt4_NumeroComanda;

    @FXML
    private TextField txt4_TelefoneContato;
    
    private final Set<Integer> numerosGerados = new HashSet<>();

    
    @FXML
    public void initialize() {
        
        
        btn4_GerarComanda.setOnAction(event -> gerarNumeroComanda());
        txt4_NumeroComanda.setEditable(false);  
        
        
        btn4_Voltar.setOnAction(event -> {
            try {
                voltarParaPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        
        btn4_GerenciarComanda.setOnAction(event -> {
            
            try {
                mudarParaQuinary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn4_AbrirComanda.setOnAction(event -> AbrirComanda());
        btn4_Pesquisar.setOnAction(event -> pesquisarPorCpf());

        
        setHandCursor(btn4_AbrirComanda);
        setHandCursor(btn4_GerarComanda);
        setHandCursor(btn4_Pesquisar);
        setHandCursor(btn4_Voltar);
        setHandCursor(btn4_GerenciarComanda);  
        
        setHandCursor(txt4_Cpf);
        setHandCursor(txt4_NomeCompleto);
        setHandCursor(txt4_TelefoneContato);

    }


    //funcionalidade do botão gerar comanda
    private void gerarNumeroComanda() {
         if (CamposVazios4()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor, preencha todos os campos.");
            alert.setContentText("Certifique-se de que todos os campos estejam preenchidos antes de salvar.");
            alert.showAndWait();
        } else {
            Random random = new Random();
            int numeroComanda;
            do {
                numeroComanda = 1000 + random.nextInt(9000); 
            } while (numerosGerados.contains(numeroComanda));
            numerosGerados.add(numeroComanda);
            txt4_NumeroComanda.setText(String.valueOf(numeroComanda));
        }
    }
       
    
    // Funcionalidade do botão pesquisar
    private void pesquisarPorCpf() {
        String cpf = txt4_Cpf.getText();

        if (cpf.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo vazio");
            alert.setHeaderText("Por favor, insira um CPF para pesquisar.");
            alert.showAndWait();
            return;
        }

        Cliente cliente = ClienteDAO.buscarPorCpf(cpf);

        if (cliente != null) {
            txt4_NomeCompleto.setText(cliente.getNomeCliente());
            txt4_TelefoneContato.setText(String.valueOf(cliente.getTelefoneContato()));
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cliente não encontrado");
            alert.setHeaderText("Nenhum cliente encontrado com o CPF informado.");
            alert.showAndWait();
        }
    }
    
    //Funcionalidade do boatão abrir comanda
    private void AbrirComanda() {
        
        if (CamposVaziosComanda()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor, preencha todos os campos.");
            alert.setContentText("Certifique-se de que todos os campos estejam preenchidos antes de salvar.");
            alert.showAndWait();
        } else {
            
            limparCampos();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText("Operação realizada com sucesso");
                alert.setContentText("A operação foi concluída com êxito.");
                alert.showAndWait();
        }
    }
    
    
    
//------------------------------------------------------------------------------------------------//
    
    // Método para verificar se os campos estão vazios
    private boolean CamposVaziosComanda() {
        return txt4_NumeroComanda.getText().isEmpty() ||
                txt4_TelefoneContato.getText().isEmpty() ||
                txt4_NomeCompleto.getText().isEmpty() ||
                txt4_Cpf.getText().isEmpty();
    } 
    private boolean CamposVazios4() {
        return txt4_TelefoneContato.getText().isEmpty() ||
                txt4_NomeCompleto.getText().isEmpty() ||
                txt4_Cpf.getText().isEmpty();
    }
    
    
    //Método para Limpar campos 
     private void limparCampos() {
        txt4_NomeCompleto.clear();
        txt4_Cpf.clear();
        txt4_TelefoneContato.clear();
        txt4_NumeroComanda.clear();
    }
     
     
    // Método para voltar para o primary.fxml
    private void voltarParaPrimary() throws IOException {
        Parent primaryRoot = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Stage stage = (Stage) btn4_Voltar.getScene().getWindow();
        stage.setScene(new Scene(primaryRoot));
    }

    // Método para mudar para o quinary.fxml
    private void mudarParaQuinary() throws IOException {
        Parent quinaryRoot = FXMLLoader.load(getClass().getResource("quinary.fxml"));
        Stage stage = (Stage) btn4_GerarComanda.getScene().getWindow();
        stage.setScene(new Scene(quinaryRoot));
    }

    // Método para definir o cursor de mão para um botão ou TextField
    private void setHandCursor(javafx.scene.Node node) {
        node.setCursor(Cursor.HAND);
    }
}