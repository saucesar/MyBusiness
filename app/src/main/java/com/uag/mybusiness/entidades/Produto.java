package com.uag.mybusiness.entidades;

import java.util.Date;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private String dataEntrada;
    private double precoCompra;
    private double precoVenda;
    private int ativo;

    public Produto(){

    }

    public Produto(int id,String nome, int quantidade, String dataEntrada, double precoCompra,double precoVenda,int ativo){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.ativo = ativo;
    }

    public Produto(String nome, int quantidade, String dataEntrada, double precoCompra,double precoVenda,int ativo){
        this.id = 0;
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
