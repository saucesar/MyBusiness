package com.uag.mybusiness.dados;

import com.uag.mybusiness.entidades.Cliente;

import java.util.List;

public interface IDadosCliente {
    public int inserir(Cliente cliente);
    public List<Cliente> listarClientes();
    public boolean excluir(String cpfCliente);
    public boolean atualizar(Cliente cliente);
}
