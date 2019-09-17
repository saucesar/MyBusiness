package com.uag.mybusiness.negocio;

import android.content.Context;

import com.uag.mybusiness.dados.ClienteDados;
import com.uag.mybusiness.entidades.Cliente;

public class NegocioCliente {
    private ClienteDados clientes;

    public NegocioCliente(Context context){
        this.clientes = new ClienteDados(context);
    }

    public void inserir(Cliente cliente){
        this.clientes.inserir(cliente);
    }
}
