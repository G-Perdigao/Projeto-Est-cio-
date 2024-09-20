package com.mycompany.loja;

import java.util.ArrayList;
import java.util.List;

public class Cliente{
    private int id;
    private final String nome;
    private final List<Venda> vendas;
    private double saldoDevedor;

    Cliente(int id, String nome, List<Venda> vendas, double saldoDevedor) {
        this.id = id;
        this.nome = nome;
        this.vendas = vendas;
        this.saldoDevedor = saldoDevedor;
    }

    public void setId(int Id) {
        this.id = Id;
    }
    
    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
        if (!venda.isPaga()) {
            saldoDevedor += venda.getValor();
        }
    }
}
