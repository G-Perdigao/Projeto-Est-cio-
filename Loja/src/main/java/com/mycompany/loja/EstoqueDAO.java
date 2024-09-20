package com.mycompany.loja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    // Método para adicionar um novo produto ao estoque
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO estoque (nome, quantidade) VALUES (?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    produto.setId(generatedKeys.getInt(1)); // Define o ID gerado para o produto
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um produto por ID
    public Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM estoque WHERE id = ?";
        Produto produto = null;
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");

                produto = new Produto(id, nome, preco, quantidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    // Método para listar todos os produtos no estoque
    public List<Produto> listarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");

                Produto produto = new Produto(id, nome, preco, quantidade);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para atualizar um produto existente
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE estoque SET nome = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setInt(3, produto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um produto por ID
    public void deletarProduto(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
