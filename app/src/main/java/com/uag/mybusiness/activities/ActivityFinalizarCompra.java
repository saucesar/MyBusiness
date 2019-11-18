package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;

import java.util.ArrayList;

public class ActivityFinalizarCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);



        ArrayList lista = (ArrayList) getIntent().getSerializableExtra("lista");
        //ArrayList<Produto> myList = (ArrayList<Produto>) getIntent().getSerializableExtra("lista");

        System.out.println("Pessoas: "+ lista.size());
        //System.out.println("Pessoas: "+ myList.size());
        /*Bundle objeto = getIntent().getExtras();
        if(objeto != null){
            String palavra = objeto.getString("nome");
            int numero = objeto.getInt("inteiro");
            System.out.println(numero);
        }

        String nome =(String) getIntent().getStringExtra("nome");
        String sobrenome =(String) getIntent().getStringExtra("sobrenome");

        System.out.println(nome);
        System.out.println(sobrenome);
        */

    }
}
