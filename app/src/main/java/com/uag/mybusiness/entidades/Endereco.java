package com.uag.mybusiness.entidades;

public class Endereco {
    private String rua, numero, bairro, cidade, estado,cpfCliente;
    private int id;

    public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cpfCliente){
        this.id = 0;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cpfCliente = cpfCliente;
    }

    public Endereco(int id,String rua, String numero, String bairro, String cidade, String estado, String cpfCliente){
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cpfCliente = cpfCliente;
    }

    public int getId(){return this.id;}
    public String getCpfCliente(){return this.cpfCliente;}
    public void setCpfCliente(String cpfCliente){this.cpfCliente = cpfCliente;}
    public String getRua(){ return this.rua; }
    public void setRua(String rua){this.rua = rua; }
    public String getNumero(){ return this.numero; }
    public void setNumero(String numero){this.numero = numero; }
    public String getBairro(){ return this.bairro; }
    public void setBairro(String bairro){this.bairro = bairro; }
    public String getCidade(){ return this.cidade; }
    public void setCidade(String cidade){this.cidade = cidade; }
    public String getEstado(){ return this.estado; }
    public void setEstado(String estado){this.estado = estado; }

    @Override
    public String toString(){ return rua+", N: "+numero+", "+bairro+", "+cidade+", "+estado; }
}
