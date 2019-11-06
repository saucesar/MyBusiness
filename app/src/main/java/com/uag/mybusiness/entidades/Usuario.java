package com.uag.mybusiness.entidades;

public class Usuario {
    private int id;
    private String login, senha;
    private boolean lembrar;

    public Usuario(String login, String senha){
        this.id = 0;
        this.login = login;
        this.senha = senha;
        this.lembrar = false;
    }

    public Usuario(int id,String login, String senha, boolean lembrar){
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.lembrar = lembrar;
    }

    public int getId(){return this.id;}
    public String getLogin(){return this.login;}
    public void setLogin(String login){this.login = login;}
    public String getSenha(){return this.senha;}
    public void setSenha(String senha){this.senha = senha;}
    public boolean getLembrar() { return lembrar;}
    public void setLembrar(boolean lembrar){this.lembrar = lembrar;}

    public boolean autenticar(String senha){
        return this.senha.equals(senha);
    }
}
