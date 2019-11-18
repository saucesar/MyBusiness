package com.uag.mybusiness.entidades;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private int id;
    private String data;
    private int idCliente;
    private List<Produto> listaProdutos = new ArrayList<>();
    private double totalCompra;

    public Carrinho(){

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
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }


    public List<Produto> adicionarProdutoCarrinho(Produto produto){
        listaProdutos.add(produto);
        return listaProdutos;
    }


}
