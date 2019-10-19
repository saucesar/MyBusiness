package com.uag.mybusiness.dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;

import java.util.ArrayList;
import java.util.List;

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
            values.put("descricao", produto.getDescricao());
            values.put("ativo", produto.getAtivo());

            int codigoProdutoInserido = (int) db.insert("produtos", null, values);

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

    public List<Produto> listarProdutos(){

        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produtos;";

        try{
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()){
                Produto produtoTemporario = null;

                do{
                    produtoTemporario = new Produto();
                    produtoTemporario.setId(cursor.getInt(0));
                    produtoTemporario.setNome(cursor.getString(1));
                    produtoTemporario.setQuantidade(cursor.getInt(2));
                    produtoTemporario.setDataEntrada(cursor.getString(3));
                    produtoTemporario.setPrecoCompra(cursor.getDouble(4));
                    produtoTemporario.setPrecoVenda(cursor.getDouble(5));
                    produtoTemporario.setDescricao(cursor.getString(6));
                    produtoTemporario.setAtivo(cursor.getInt(7));

                    listaProdutos.add(produtoTemporario);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("Erro ao acessar a lista", "Erro ao acessar produtos no banco");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }

        return listaProdutos;

    }



}
