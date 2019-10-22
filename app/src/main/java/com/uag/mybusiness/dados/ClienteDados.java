package com.uag.mybusiness.dados;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Cliente;
import com.uag.mybusiness.entidades.Endereco;
import com.uag.mybusiness.entidades.Produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
        EnderecoValues.put("cpfCliente",cliente.getCpf());

        SQLiteDatabase db = this.conexaoSQLite.getWritableDatabase();
        db.insert("Clientes",null, ClienteValues);
        db.insert("Enderecos",null, EnderecoValues);
    }

    public List<Cliente> listarClientes(){
        List<Cliente> clientes = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursorClientes;
        Cursor cursorEnderecos;

        String queryClientes = "SELECT * FROM clientes";

        try{
            db = this.conexaoSQLite.getReadableDatabase();
            cursorClientes = db.rawQuery(queryClientes, null);


            if (cursorClientes.moveToFirst()){
                Cliente tempCliente;
                Endereco enderecoTemp;

                do{
                    int idCliente = cursorClientes.getInt(0);
                    String nome = cursorClientes.getString(1);
                    String cpf = cursorClientes.getString(2);

                    String queryEnderecos = "SELECT * FROM enderecos WHERE cpfCliente = "+cpf;
                    cursorEnderecos = db.rawQuery(queryEnderecos, null);

                    cursorEnderecos.moveToFirst();

                    int idEndereco = cursorEnderecos.getInt(0);
                    String rua = cursorEnderecos.getString(1);
                    String numero = cursorEnderecos.getString(2);
                    String bairro = cursorEnderecos.getString(3);
                    String cidade = cursorEnderecos.getString(4);
                    String estado = cursorEnderecos.getString(5);

                    enderecoTemp = new Endereco(idEndereco, rua, numero, bairro, cidade, estado, cpf);
                    tempCliente = new Cliente(idCliente, nome, cpf, enderecoTemp);

                    clientes.add(tempCliente);
                }while (cursorClientes.moveToNext());
            }
        }catch (Exception e){
            Log.d("Erro ao acessar a lista", "Erro ao acessar clientes no banco");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return clientes;
    }
}
