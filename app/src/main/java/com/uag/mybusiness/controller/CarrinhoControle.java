package com.uag.mybusiness.controller;

import com.uag.mybusiness.dados.CarrinhoDados;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Carrinho;
import com.uag.mybusiness.entidades.Produto;

import java.util.List;

public class CarrinhoControle {
    private final CarrinhoDados carrinhoDados;
    private Carrinho carrinho;



    public CarrinhoControle(ConexaoSQLite nConexaoSQLite, CarrinhoDados carrinhoDados){
        this.carrinhoDados = carrinhoDados;
        carrinhoDados = new CarrinhoDados(nConexaoSQLite);
    }


    public int salvarClienteControle(Carrinho carrinho){
        return this.carrinhoDados.inserir(carrinho);
    }

    public List<Carrinho> listarCarrinhoControle(){
        return this.carrinhoDados.listarCarrinho();
    }

    public boolean excluirClienteControle(String idCarrinho){
        return this.carrinhoDados.excluir(idCarrinho);
    }

    public boolean atualizarCarrinhoControle(Carrinho carrinho){
        return this.carrinhoDados.atualizar(carrinho);
    }

    public List<Produto> adionarProdutoCarrinhoControle(Produto produto){
        return carrinho.adicionarProdutoCarrinho(produto);
    }
}
