package com.uag.mybusiness.entidades;

import java.io.Serializable;
import java.util.Date;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private int quantidade;
    private String dataEntrada;
    private double precoCompra;
    private double precoVenda;
    private String descricao;
    private int ativo;
    private String fotoPrincipal;
    private String fotoSecundaria;

    public Produto(){

    }

    public Produto(int id,String nome, int quantidade, String dataEntrada, double precoCompra,double precoVenda, String descricao, int ativo){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.descricao = descricao;
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

    public Produto(int id,String nome, int quantidade, String dataEntrada, double precoCompra,double precoVenda, String descricao,
            String fotoPrincipal, String fotoSecundaria, int ativo){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.descricao = descricao;
        this.fotoPrincipal = fotoPrincipal;
        this.fotoSecundaria = fotoSecundaria;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(String fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public String getFotoSecundaria() {
        return fotoSecundaria;
    }

    public void setFotoSecundaria(String fotoSecundaria) {
        this.fotoSecundaria = fotoSecundaria;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
