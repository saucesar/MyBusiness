package com.uag.mybusiness.dados;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;

public class ProdutoDados {

    private final ConexaoSQLite conexaoSQLite;

    public ProdutoDados (ConexaoSQLite conexaoSQLite){
        this.conexaoSQLite = conexaoSQLite;
    }

    public int salvarProdutoDados(Produto produto){
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("nome", produto.getNome());
            values.put("quantidade", produto.getQuantidade());
            values.put("data", produto.getDataEntrada());
            values.put("precoCompra", produto.getPrecoCompra());
            values.put("precoVenda", produto.getPrecoVenda());
            values.put("ativo", produto.getAtivo());

            int codigoProdutoInserido = (int) db.insert("Produtos", null, values);

            return codigoProdutoInserido;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }

        }
        return 0;

    }



}