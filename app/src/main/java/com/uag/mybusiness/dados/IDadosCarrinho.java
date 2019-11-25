package com.uag.mybusiness.dados;

import com.uag.mybusiness.entidades.Carrinho;
import com.uag.mybusiness.entidades.Cliente;

import java.util.List;

public interface IDadosCarrinho {
    public int inserir(Carrinho carrinho);
    public List<Carrinho> listarCarrinho();
    public boolean excluir(int idCarrinho);
    public boolean atualizar(Carrinho carrinho);
}
