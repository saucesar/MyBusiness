package com.uag.mybusiness.entidades;

public enum  EnumUser {
    FUNCIONARIO, ADMINISTRADOR;

    public static EnumUser valueOf(int i){
        if (i == 0){return EnumUser.FUNCIONARIO;}
        else{return EnumUser.ADMINISTRADOR;}
    }
}
