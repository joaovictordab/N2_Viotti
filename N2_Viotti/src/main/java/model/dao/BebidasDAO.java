package model.dao;

import model.bean.Bebidas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.connection.MySQLWorkBench;

public class BebidasDAO {
    public boolean salvar(Bebidas bebida) {
        String sql = "INSERT INTO bebidas (nome, descricao, preco, quantidade_ml, tipo, alcoolica,codigoBebida) VALUES (?, ?, ?, ?, ?, ? ,? )";

        try (Connection conn = MySQLWorkBench.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bebida.getNome());
            stmt.setString(2, bebida.getDescricao());
            stmt.setDouble(3, bebida.getPreco());
            stmt.setInt(4, bebida.getQuantidadeML());
            stmt.setString(5, bebida.getTipo());
            stmt.setBoolean(6, bebida.isAlcoolica());
            stmt.setInt(7,bebida.getCodigoBebida());

            stmt.executeUpdate();
            return true; // Retorna true se a inserção for bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false se houver algum erro
        }
    }
}
