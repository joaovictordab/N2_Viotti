package com.mycompany.n2_viotti;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter;
import model.bean.Cliente;
import model.dao.ClienteDAO;

public class TertiaryController {

    @FXML
    private Button btn3_Editar;

    @FXML
    private Button btn3_Excluir;

    @FXML
    private Button btn3_Pesquisar;

    @FXML
    private Button btn3_Salvar;

    @FXML
    private DatePicker datap3_Nascimento;

    @FXML
    private TextField txt3_Cpf;

    @FXML
    private TextField txt3_MaiorIdade;

    @FXML
    private TextField txt3_NomeCompleto;

    @FXML
    private TextField txt3_Rg;

    @FXML
    private TextField txt3_Telefone; 
    
    @FXML
    private TextField txt3_Email;
    
    @FXML
    private Button btn3_Voltar;

    public void initialize() {
        btn3_Voltar.setOnAction(event -> {
            try {
                voltarParaPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        //Configuração dos dados recebidos
        permitirSomenteNumeros(txt3_Cpf, 11);
        permitirSomenteNumeros(txt3_Telefone, 13);
        limitarCaracteres(txt3_Cpf, 11);
        limitarCaracteres(txt3_Rg, 11);
        limitarCaracteres(txt3_NomeCompleto, 100);

        datap3_Nascimento.setOnAction(event -> calcularMaiorIdade());

        setHandCursor(btn3_Editar);
        setHandCursor(btn3_Excluir);
        setHandCursor(btn3_Pesquisar);
        setHandCursor(btn3_Salvar);
        setHandCursor(btn3_Voltar);

        btn3_Salvar.setOnAction(event -> salvar());
        btn3_Pesquisar.setOnAction(event -> pesquisarPorCpf());
        btn3_Editar.setOnAction(event -> alterarCliente());
        btn3_Excluir.setOnAction(event -> excluir());
       

    }
    
    // funcionalidade do botão salvar
    private void salvar() {
        if (txt3_MaiorIdade.getText().equals("Não")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Impossível salvar");
            alert.setContentText("O cliente não pode consumir bebida alcoólica pois é menor de idade.");
            alert.showAndWait();
        } else {
            if (camposVazios3()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Campos obrigatórios");
                alert.setHeaderText("Por favor, preencha todos os campos.");
                alert.setContentText("Certifique-se de que todos os campos estejam preenchidos antes de salvar.");
                alert.showAndWait();
            } else {
                try {

                    Cliente cliente = new Cliente();
                    cliente.setNomeCliente(txt3_NomeCompleto.getText());
                    cliente.setCpf(Integer.parseInt(txt3_Cpf.getText())); 
                    cliente.setRg(txt3_Rg.getText());
                    cliente.setTelefoneContato(Integer.parseInt(txt3_Telefone.getText())); 
                    cliente.setEmail(txt3_Email.getText());
                    cliente.setDataNascimento(datap3_Nascimento.getValue());
                    cliente.setMaioridade(txt3_MaiorIdade.getText());


                    ClienteDAO clienteDAO = new ClienteDAO();
                    boolean sucesso = clienteDAO.salvar(cliente);

                    if (sucesso) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Sucesso");
                        alert.setHeaderText("Cadastro realizado com sucesso!");
                        alert.setContentText("As informações foram salvas no banco de dados.");
                        alert.showAndWait();
                        
                        limparCampos();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText("Falha ao salvar");
                        alert.setContentText("Ocorreu um erro ao salvar as informações no banco de dados.");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro de Formato");
                    alert.setHeaderText("Dados inválidos");
                    alert.setContentText("CPF e Telefone devem ser números inteiros.");
                    alert.showAndWait();
                }
            }
        }
    }
    
    
    // Funcionalidade do botão pesquisar
    private void pesquisarPorCpf() {
        String cpf = txt3_Cpf.getText(); 

        if (cpf.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo vazio");
            alert.setHeaderText("Por favor, insira um CPF para pesquisar.");
            alert.showAndWait();
            return;
        }

        Cliente cliente = ClienteDAO.buscarPorCpf(cpf); 

        if (cliente != null) {
            // Preenche os campos com os dados do cliente retornado
            txt3_NomeCompleto.setText(cliente.getNomeCliente());
            txt3_Rg.setText(cliente.getRg());
            txt3_Telefone.setText(String.valueOf(cliente.getTelefoneContato()));
            txt3_Email.setText(cliente.getEmail());
            datap3_Nascimento.setValue(cliente.getDataNascimento());
            txt3_MaiorIdade.setText(cliente.getMaioridade()); 
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cliente não encontrado");
            alert.setHeaderText("Nenhum cliente encontrado com o CPF informado.");
            alert.showAndWait();
        }
    }
    
    
    //Funcionalidade do botão alterar
    private void alterarCliente() {
    if (txt3_Cpf.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("CPF não informado");
        alert.setContentText("Por favor, informe o CPF do cliente para atualizar.");
        alert.showAndWait();
        return;
    }

    try {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(txt3_NomeCompleto.getText());
        cliente.setCpf(Integer.parseInt(txt3_Cpf.getText())); 
        cliente.setRg(txt3_Rg.getText());
        cliente.setTelefoneContato(Integer.parseInt(txt3_Telefone.getText()));
        cliente.setEmail(txt3_Email.getText());
        cliente.setDataNascimento(datap3_Nascimento.getValue());
        cliente.setMaioridade(txt3_MaiorIdade.getText());

        ClienteDAO clienteDAO = new ClienteDAO();
        boolean sucesso = clienteDAO.alterar(cliente);

        if (sucesso) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Atualização realizada");
            alert.setContentText("As informações do cliente foram atualizadas com sucesso.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro na atualização");
            alert.setContentText("Não foi possível atualizar as informações. Verifique os dados e tente novamente.");
            alert.showAndWait();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Formato");
        alert.setHeaderText("Dados inválidos");
        alert.setContentText("CPF e Telefone devem ser números inteiros.");
        alert.showAndWait();
    }
}
    
    //Funcionalidade do botão excluir
    private void excluir() {
    String cpf = txt3_Cpf.getText();

    if (cpf.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText("CPF não informado");
        alert.setContentText("Por favor, insira o CPF do cliente que deseja excluir.");
        alert.showAndWait();
        return;
    }

    ClienteDAO clienteDAO = new ClienteDAO();
    boolean sucesso = clienteDAO.excluirPorCpf(cpf);

    if (sucesso) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Exclusão realizada");
        alert.setContentText("O cliente foi excluído com sucesso.");
        alert.showAndWait();

        limparCampos();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Falha na exclusão");
        alert.setContentText("Não foi possível excluir o cliente. Verifique se o CPF está correto.");
        alert.showAndWait();
    }
}
    
    
    //------------------------------------------------------------------------------------------------//
    private void voltarParaPrimary() throws IOException {
        Parent primaryRoot = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Stage stage = (Stage) btn3_Voltar.getScene().getWindow();
        stage.setScene(new Scene(primaryRoot));
    }

    private void setHandCursor(Button button) {
        button.setCursor(Cursor.HAND);
    }
    
    
    //método limpar campos
    private void limparCampos() {
    txt3_NomeCompleto.clear();
    txt3_Cpf.clear();
    txt3_Rg.clear();
    txt3_Telefone.clear();
    txt3_Email.clear();
    txt3_MaiorIdade.clear();
    datap3_Nascimento.setValue(null);
}
    
    //Método para calcular a idade
    private void calcularMaiorIdade() {
        if (datap3_Nascimento.getValue() != null) {
            LocalDate dataNascimento = datap3_Nascimento.getValue();
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataNascimento, dataAtual);
            int idade = periodo.getYears();

            if (idade >= 18) {
                txt3_MaiorIdade.setText("Sim");
            } else {
                txt3_MaiorIdade.setText("Não");
            }
        }
    }
     
    //Método para a verificação de campos vazios
    private boolean camposVazios3() {
        return txt3_NomeCompleto.getText().isEmpty() || 
               txt3_Cpf.getText().isEmpty() ||
               txt3_Rg.getText().isEmpty() ||
               txt3_Telefone.getText().isEmpty() || // Alterado para telefoneContato
               txt3_Email.getText().isEmpty() ||
               datap3_Nascimento.getValue() == null;
    }
    
    
    //Médoto para Proibir a entrada de string em campos não permitidos
    private void permitirSomenteNumeros(TextField textField, int maxLength) {
    textField.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*") && newText.length() <= maxLength) {
            return change;
        }
        return null; 
    }));
}   


    //Médodo para limitar os caracteres recebidos
    public void limitarCaracteres(TextField textField, int limite) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.length() > limite) {
                textField.setText(oldValue); 
            }
    });
}
}

