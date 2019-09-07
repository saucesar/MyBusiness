package com.uag.mybusiness.repositorio;

import com.uag.mybusiness.entidades.Cliente;
import java.util.ArrayList;

public class RepositorioCliente {
    private ArrayList<Cliente> clientes;

    public RepositorioCliente(){this.clientes = new ArrayList<Cliente>();}

    public boolean adicionarCliente(Cliente c){
        clientes.add(c);
        return true;
    }

    public Cliente buscarPorCpf(String cpf){
        Cliente c = null;
        for(Cliente cliente:this.clientes){
            if(cliente.getCpf().equals(cpf)){
                c = cliente;
                break;
            }
        }
        return c;
    }

    public boolean removerCliente(String cpf){
        boolean removeu = false;
        Cliente c = buscarPorCpf(cpf);
        if(c != null){
            clientes.remove(c);
            removeu = true;
        }
        return removeu;
    }

    @Override
    public String toString(){
        String str = "";
        for(Cliente cliente:this.clientes){
            str+= "__________________________________________________";
            str+= cliente.toString();
        }
        return str;
    }
}
