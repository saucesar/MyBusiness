package com.uag.mybusiness.controller;

import com.uag.mybusiness.dados.ProdutoDados;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;

import java.util.List;

public class ProdutoControle {

    private final ProdutoDados produtoDados;

    public ProdutoControle(ConexaoSQLite nConexaoSQLite){
        produtoDados = new ProdutoDados(nConexaoSQLite);
    }

    public int salvarProdutoControle(Produto produto){
        return this.produtoDados.salvarProdutoDados(produto);
    }

    public List<Produto> listarProdutoControle(String nomeProduto){
        return this.produtoDados.listarProdutos(nomeProduto);
    }

    public boolean excluirProdutoControle(int produto){
        return this.produtoDados.excluirProdutoDados(produto);
    }

    public boolean atualizarProdutoControle(Produto produto){
        return this.produtoDados.atualizarProduto(produto);
    }

}
