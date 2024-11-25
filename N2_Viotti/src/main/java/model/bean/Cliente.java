package model.bean;

import java.time.LocalDate;

public class Cliente {
    private String nomeCliente;
    private int cpf;
    private String rg;
    private int telefoneContato;
    private String email;
    private LocalDate dataNascimento;
    private String maioridade;

    // Implementação do método setNomeCliente
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    // Outros métodos getters e setters...

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRg() {
        return rg;
    }

    public void setTelefoneContato(int telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public int getTelefoneContato() {
        return telefoneContato;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setMaioridade(String maioridade) {
        this.maioridade = maioridade;
    }
    
    public String getMaioridade() {
        return maioridade;
    }
}
