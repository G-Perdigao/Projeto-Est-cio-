package com.mycompany.loja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class VendaDAO {

    // Método para adicionar uma nova venda
    public void adicionarVenda(Venda venda) {
        String sql = "INSERT INTO vendas (cliente_id, data_venda, paga, valor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setInt(1, venda.getClienteId());
            stmt.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));  // Converte java.util.Date para java.sql.Date
            stmt.setBoolean(3, venda.isPaga());
            stmt.setDouble(4, venda.getValor());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    venda.setId(generatedKeys.getInt(1));  // Define o ID gerado para a venda
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar uma venda por ID
    public Venda buscarVendaPorId(int id) {
        String sql = "SELECT * FROM vendas WHERE id = ?";
        Venda venda = null;
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int vendaId = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                Date dataVenda = rs.getDate("data_venda");
                boolean paga = rs.getBoolean("paga");
                double valor = rs.getDouble("valor");

                venda = new Venda(vendaId, clienteId, dataVenda, paga, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venda;
    }

    // Método para buscar todas as vendas associadas a um cliente
    public List<Venda> buscarVendasPorClienteId(int clienteId) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE cliente_id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int vendaId = rs.getInt("id");
                Date dataVenda = rs.getDate("data_venda");
                boolean paga = rs.getBoolean("paga");
                double valor = rs.getDouble("valor");

                Venda venda = new Venda(vendaId, clienteId, dataVenda, paga, valor);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    // Método para atualizar uma venda existente
    public void atualizarVenda(Venda venda) {
        String sql = "UPDATE vendas SET cliente_id = ?, data_venda = ?, paga = ?, valor = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venda.getClienteId());
            stmt.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setBoolean(3, venda.isPaga());
            stmt.setDouble(4, venda.getValor());
            stmt.setInt(5, venda.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma venda por ID
    public void deletarVenda(int id) {
        String sql = "DELETE FROM vendas WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar vendas por período
    public List<Venda> buscarVendasPorPeriodo(Date inicio, Date fim) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE data_venda BETWEEN ? AND ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(inicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fim.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int vendaId = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                Date dataVenda = rs.getDate("data_venda");
                boolean paga = rs.getBoolean("paga");
                double valor = rs.getDouble("valor");

                Venda venda = new Venda(vendaId, clienteId, dataVenda, paga, valor);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }
}
