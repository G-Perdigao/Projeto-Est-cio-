
package com.mycompany.loja;
import java.util.Date;

public class Venda {
    private int id;
    private String produto;
    private int clienteId;
    private Date dataVenda;
    private boolean paga;
    private double valor;
    private int quantidade;

    public Venda(int id, int clienteId, Date dataVenda, boolean paga, double valor) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataVenda = dataVenda;
        this.paga = paga;
        this.valor = valor;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}