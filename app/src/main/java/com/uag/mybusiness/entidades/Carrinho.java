package com.uag.mybusiness.entidades;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private int id;
    private String data;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private double totalCompra;

    public Carrinho(){
        this.id = 0;
        this.data = "18/11/2019";
        this.cliente = null;
        this.listaProdutos = new ArrayList<>();
        this.totalCompra = 0;
    }

    public void setListaProdutos(List<Produto> produtos){
        this.listaProdutos = produtos;
        for(Produto p:this.listaProdutos){this.totalCompra += p.getPrecoVenda();}
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdCliente() {
        return cliente.getId();
    }

    public void setCliente(Cliente novoCliente) {
        this.cliente = novoCliente;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<Produto> getListaProdutos(){return this.listaProdutos;}

    public List<Produto> adicionarProdutoCarrinho(Produto produto){
        listaProdutos.add(produto);
        return listaProdutos;
    }


}
