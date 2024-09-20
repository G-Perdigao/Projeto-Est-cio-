package com.mycompany.loja;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para adicionar um novo cliente
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, saldo_devedor) VALUES (?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setDouble(2, cliente.getSaldoDevedor());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1)); // Define o ID gerado para o cliente
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um cliente por ID
    public Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int clienteId = rs.getInt("id");
                String nome = rs.getString("nome");
                double saldoDevedor = rs.getDouble("saldo_devedor");

                // Busca as vendas associadas ao cliente
                VendaDAO vendaDAO = new VendaDAO();
                List<Venda> vendas = vendaDAO.buscarVendasPorClienteId(clienteId);

                // Cria o objeto Cliente com os dados obtidos
                cliente = new Cliente(clienteId, nome, vendas, saldoDevedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Método para listar todos os clientes
    public List<Cliente> listarTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int clienteId = rs.getInt("id");
                String nome = rs.getString("nome");
                double saldoDevedor = rs.getDouble("saldo_devedor");

                // Busca as vendas associadas ao cliente
                VendaDAO vendaDAO = new VendaDAO();
                List<Venda> vendas = vendaDAO.buscarVendasPorClienteId(clienteId);

                // Cria o objeto Cliente e o adiciona à lista
                Cliente cliente = new Cliente(clienteId, nome, vendas, saldoDevedor);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para atualizar um cliente
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, saldo_devedor = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setDouble(2, cliente.getSaldoDevedor());
            stmt.setInt(3, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um cliente por ID
    public void deletarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
