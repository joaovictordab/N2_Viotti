package model.bean;

public class Bebidas {

    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeML;
    private String tipo;
    private boolean alcoolica;
    private int codigoBebida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeML() {
        return quantidadeML;
    }

    public void setQuantidadeML(int quantidadeML) {
        this.quantidadeML = quantidadeML;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAlcoolica() {
        return alcoolica;
    }

    public void setAlcoolica(boolean alcoolica) {
        this.alcoolica = alcoolica;
    }

    public int getCodigoBebida() {
        return codigoBebida;
    }

    public void setCodigoBebida(int codigoBebida) {
        this.codigoBebida = codigoBebida;
    }

    public Bebidas(String name, String descricao, double preco, int quantidadeML, String tipo, boolean alcoolica,
            int codigoBebida) {
        this.nome = name;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeML = quantidadeML;
        this.tipo = tipo;
        this.alcoolica = alcoolica;
        this.codigoBebida = codigoBebida;
    }

}
