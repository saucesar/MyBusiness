package com.uag.mybusiness.entidades;

public class Cliente {

    private int id;
    private String nome, cpf;
    private Endereco endereco;

    public Cliente(int id, String nome, String cpf, Endereco endereco){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }
    public Cliente(String nome, String cpf, Endereco endereco){
        this.id = 0;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome(){ return this.nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf(){ return this.cpf; }
    public void setCpf(String cpf){ this.cpf = cpf;}
    public Endereco getEndereco(){return this.endereco;}

    @Override
    public String toString(){
        return "\nNome: "+this.nome+"\nCPF: "+this.cpf+"\nEndereco: "+this.endereco.toString();
    }
}
