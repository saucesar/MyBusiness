package com.uag.mybusiness.controller;
import com.uag.mybusiness.dados.IDadosUsuario;
import com.uag.mybusiness.dados.UsuarioDados;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Usuario;

import java.util.List;

public class UsuarioControle{
    private final IDadosUsuario usuarioDados;

    public UsuarioControle(ConexaoSQLite nConexaoSQLite){
        this.usuarioDados = new UsuarioDados(nConexaoSQLite);
    }

    public boolean inserir(Usuario user) {
        return usuarioDados.inserir(user);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDados.listarUsuarios();
    }

    public boolean excluir(int id) {
        return usuarioDados.excluir(id);
    }

    public boolean atualizar(Usuario user) {
        return usuarioDados.atualizar(user);
    }

    public Usuario buscarPorLogin(String login){return usuarioDados.buscarUsuario(login);}

    public boolean autenticar(String login, String senha){
        return usuarioDados.autenticar(login,senha);
    }
}
