package com.uag.mybusiness.dados;

import com.uag.mybusiness.entidades.Usuario;

import java.util.List;

public interface IDadosUsuario {
    boolean inserir(Usuario user);
    List<Usuario> listarUsuarios();
    boolean excluir(int id);
    boolean atualizar(Usuario user);
    boolean autenticar(String login, String senha);
}
