package com.uag.mybusiness.repositorio;

import com.uag.mybusiness.entidades.Endereco;
import java.util.ArrayList;

public class RepositorioEndereco {
    private ArrayList<Endereco> enderecos;

    public RepositorioEndereco(){this.enderecos = new ArrayList<Endereco>();}

    public boolean adicionarEndereco(Endereco e){
        this.enderecos.add(e);
        return true;
    }

    public Endereco buscarEndereco(String cpfCliente){
        Endereco e = null;
        for(Endereco endereco:this.enderecos){
            if(endereco.getCpfCliente().equals(cpfCliente)){
                e = endereco;
                break;
            }
        }
        return e;
    }

    public boolean removerEndereco(String cpfCliente){
        boolean removeu = false;
        Endereco e = buscarEndereco(cpfCliente);
        if(e != null){
            enderecos.remove(e);
            removeu = true;
        }
        return removeu;
    }
}
