package com.uag.mybusiness.controller;

import com.uag.mybusiness.dados.ProdutoDADOS;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;

public class ProdutoControle {

    private final ProdutoDADOS produtoDADOS;

    public ProdutoControle(ConexaoSQLite nConexaoSQLite){
        produtoDADOS = new ProdutoDADOS(nConexaoSQLite);
    }

    public long salvarProdutoControle(Produto produto){
        return this.produtoDADOS.salvarProdutoDADOS(produto);
    }



}
