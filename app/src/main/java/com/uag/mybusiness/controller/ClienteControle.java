package com.uag.mybusiness.controller;

import com.uag.mybusiness.dados.ClienteDados;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Cliente;

import java.util.List;

public class ClienteControle {
    private final ClienteDados clienteDados;

    public ClienteControle(ConexaoSQLite nConexaoSQLite){
        clienteDados = new ClienteDados(nConexaoSQLite);
    }

    public int salvarClienteControle(Cliente cliente){
        return this.clienteDados.inserir(cliente);
    }

    public List<Cliente> listarClienteControle(){
        return this.clienteDados.listarClientes();
    }

    public boolean excluirClienteControle(String cpfCliente){
        return this.clienteDados.excluir(cpfCliente);
    }

    public boolean atualizarClienteControle(Cliente cliente){
        return this.clienteDados.atualizar(cliente);
    }
}
