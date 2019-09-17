package com.uag.mybusiness.dados;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Cliente;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ClienteDados {
    private ConexaoSQLite conexaoSQLite;
    public ClienteDados(Context context){
        this.conexaoSQLite = ConexaoSQLite.getInstancia(context);
    }

    public void inserir(Cliente cliente){
        ContentValues ClienteValues = new ContentValues();
        ContentValues EnderecoValues = new ContentValues();

        ClienteValues.put("nome",cliente.getNome());
        ClienteValues.put("cpf",cliente.getCpf());

        EnderecoValues.put("rua",cliente.getEndereco().getRua());
        EnderecoValues.put("numero",cliente.getEndereco().getNumero());
        EnderecoValues.put("bairro",cliente.getEndereco().getBairro());
        EnderecoValues.put("cidade",cliente.getEndereco().getCidade());
        EnderecoValues.put("estado",cliente.getEndereco().getEstado());

        SQLiteDatabase db = this.conexaoSQLite.getWritableDatabase();
        db.insert("Clientes",null, ClienteValues);
        db.insert("Enderecos",null, EnderecoValues);
    }
}
