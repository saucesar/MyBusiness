package com.uag.mybusiness.entidades;

public class Endereco {
    private String rua, numero, bairro, cidade, estado,cpfCliente;

    public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cpfCliente){
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cpfCliente = cpfCliente;
    }

    public String getCpfCliente(){return this.cpfCliente;}
    public void setCpfCliente(String cpfCliente){this.cpfCliente = cpfCliente;}
    public void setRua(String rua){this.rua = rua; }
    public void setNumero(String numero){this.numero = numero; }
    public void setBairro(String bairro){this.bairro = bairro; }
    public void setCidade(String cidade){this.cidade = cidade; }
    public void setEstado(String estado){this.estado = estado; }

    @Override
    public String toString(){ return rua+", N: "+numero+", "+bairro+", "+cidade+", "+estado; }
}
