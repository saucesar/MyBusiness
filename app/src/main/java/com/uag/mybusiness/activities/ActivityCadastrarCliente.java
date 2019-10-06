package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uag.mybusiness.R;

public class ActivityCadastrarCliente extends AppCompatActivity {

    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        this.buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        this.buttonSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela cliente
                Intent intent = new Intent(ActivityCadastrarCliente.this, ActivityCadastrarCliente.class);
                startActivity(intent);
                clikSalvar();
            }
        });
    }

    public void clikSalvar(){
        Toast.makeText(ActivityCadastrarCliente.this, "Cliente Salvo com Sucesso!", Toast.LENGTH_LONG).show();
    }
}
