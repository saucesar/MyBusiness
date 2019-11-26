package com.uag.mybusiness.controller;

import com.uag.mybusiness.dados.CarrinhoDados;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Carrinho;
import com.uag.mybusiness.entidades.Produto;

import java.util.List;

public class CarrinhoControle {
    private final CarrinhoDados carrinhoDados;


    public CarrinhoControle(ConexaoSQLite nConexaoSQLite){
        this.carrinhoDados = new CarrinhoDados(nConexaoSQLite);
    }

    public int salvarCarrinhoControle(Carrinho carrinho){
        return this.carrinhoDados.inserir(carrinho);
    }

    public List<Carrinho> listarCarrinhoControle(){
        return this.carrinhoDados.listarCarrinho();
    }

    public boolean excluirCarrinhoControle(int idCarrinho){
        return this.carrinhoDados.excluir(idCarrinho);
    }

    public boolean atualizarCarrinhoControle(Carrinho carrinho){
        return this.carrinhoDados.atualizar(carrinho);
    }
}
