package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Cliente;
import model.connection.MySQLWorkBench;

public class ClienteDAO {
    
    //Salvar clientes no Banco de dados
    public boolean salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nomeCliente, cpf, rg, telefoneContato, email, dataNascimento, Maioridade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = MySQLWorkBench.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setInt(2, cliente.getCpf()); 
            stmt.setString(3, cliente.getRg());
            stmt.setInt(4, cliente.getTelefoneContato()); 
            stmt.setString(5, cliente.getEmail());
            stmt.setDate(6, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(7, cliente.getMaioridade());

            stmt.executeUpdate();
            return true; // Sucesso
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Erro
        } finally {
            MySQLWorkBench.closeConnection(conn, stmt);
        }
    }

    // Buscar cliente por CPF
    public static Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        Connection conn = MySQLWorkBench.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf); // Define o CPF para a consulta
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setNomeCliente(rs.getString("nomeCliente"));
                cliente.setCpf(rs.getInt("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setTelefoneContato(rs.getInt("telefoneContato"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                cliente.setMaioridade(rs.getString("Maioridade"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MySQLWorkBench.closeConnection(conn, stmt);
        }

        return cliente; // Retorna o cliente encontrado ou null se não encontrar
          
    }
    
    
    // Alterar as  informações de um cliente
    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE clientes SET nomeCliente = ?, rg = ?, telefoneContato = ?, email = ?, dataNascimento = ?, Maioridade = ? WHERE cpf = ?";
        Connection conn = MySQLWorkBench.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getRg());
            stmt.setInt(3, cliente.getTelefoneContato());
            stmt.setString(4, cliente.getEmail());
            stmt.setDate(5, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(6, cliente.getMaioridade());
            stmt.setInt(7, cliente.getCpf()); // CPF é usado como critério de atualização

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se ao menos uma linha foi atualizada
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Retorna false em caso de erro
        } finally {
            MySQLWorkBench.closeConnection(conn, stmt);
        }
    }
    
    
    //excluir o cliente
    public boolean excluirPorCpf(String cpf) {
    String sql = "DELETE FROM clientes WHERE cpf = ?";
    Connection conn = MySQLWorkBench.getConnection();
    PreparedStatement stmt = null;

    try {
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf); // Define o CPF para exclusão
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Retorna true se algum registro foi excluído
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false; // Retorna false em caso de erro
    } finally {
        MySQLWorkBench.closeConnection(conn, stmt);
    }
}

}

