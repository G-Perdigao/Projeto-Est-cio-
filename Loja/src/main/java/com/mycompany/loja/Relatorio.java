package com.mycompany.loja;

import java.util.List;

public class Relatorio {
    public static void gerarRelatorioVendas(List<Venda> vendas) {
        System.out.println("Relatório de Vendas:");
        for (Venda venda : vendas) {
            System.out.println("Produto: " + venda.getProduto() + ", Quantidade: " + venda.getQuantidade() + ", Data: " + venda.getDataVenda());
        }
    }

    public static void gerarRelatorioDebitos(List<Cliente> clientes) {
        System.out.println("Relatório de Débitos:");
        for (Cliente cliente : clientes) {
            if (cliente.getSaldoDevedor() > 0) {
                System.out.println("Cliente: " + cliente.getNome() + ", Débito: R$ " + cliente.getSaldoDevedor());
            }
        }
    }
}

