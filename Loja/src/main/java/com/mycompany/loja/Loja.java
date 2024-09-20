package com.mycompany.loja;

import java.util.Date;
import java.util.List;

public class Loja {
    private ClienteDAO clienteDAO;
    private VendaDAO vendaDAO;
    private EstoqueDAO estoqueDAO;

    public Loja() {
        this.clienteDAO = new ClienteDAO();
        this.vendaDAO = new VendaDAO();
        this.estoqueDAO = new EstoqueDAO();
    }

    public void cadastrarVenda(int id, int clienteId, double valor, boolean paga) {
        Cliente cliente = clienteDAO.buscarClientePorId(clienteId);
        if (cliente != null) {
            Venda venda = new Venda(id, clienteId, new Date(), paga, valor);
            vendaDAO.adicionarVenda(venda);

            if (paga) {
                cliente.setSaldoDevedor(cliente.getSaldoDevedor() - valor);
            } else {
                cliente.setSaldoDevedor(cliente.getSaldoDevedor() + valor);
            }
            clienteDAO.atualizarCliente(cliente);
        }
    }

    public List<Venda> gerarRelatorioVendas(Date inicio, Date fim) {
        List<Venda> vendas = vendaDAO.buscarVendasPorPeriodo(inicio, fim);
        System.out.println("Relatório de Vendas de " + inicio + " até " + fim + ":");
        for (Venda venda : vendas) {
            System.out.println("ID Venda: " + venda.getId() +
                    ", Cliente ID: " + venda.getClienteId() +
                    ", Data: " + venda.getDataVenda() +
                    ", Valor: " + venda.getValor() +
                    ", Paga: " + (venda.isPaga() ? "Sim" : "Não"));
        }
        return vendas;
    }

    public List<Produto> listarProdutos() {
        return estoqueDAO.listarTodosProdutos();
    }
}
