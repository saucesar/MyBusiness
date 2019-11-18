package com.uag.mybusiness.dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Carrinho;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDados implements IDadosCarrinho{
    private ConexaoSQLite conexaoSQLite;
    public CarrinhoDados(ConexaoSQLite conexaoSQLite){
        this.conexaoSQLite = conexaoSQLite;
    }

    public int inserir(Carrinho carrinho){
        ContentValues CarrinhoValues = new ContentValues();


        CarrinhoValues.put("data",carrinho.getData());
        CarrinhoValues.put("id_cliente",carrinho.getIdCliente());
        CarrinhoValues.put("totalCompra",carrinho.getTotalCompra());



        SQLiteDatabase db = this.conexaoSQLite.getWritableDatabase();
        int status = (int)db.insert("carrinhos",null, CarrinhoValues);

        return status;
    }

    public List<Carrinho> listarCarrinho(){

        ArrayList listaCarrinho = new ArrayList<>();

        return listaCarrinho;
    }

    public boolean excluir(String idCarrinho) {
        return false;
    }

    public boolean atualizar(Carrinho carrinho){

        return true;
    }
}
