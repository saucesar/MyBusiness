package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uag.mybusiness.R;

public class ActivityMenuUsuario extends AppCompatActivity {

    private Button buttonCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        this.buttonCadastrarUsuario = (Button) findViewById(R.id.buttonCadastrarUsuario);

        this.buttonCadastrarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto
                Intent intent = new Intent(ActivityMenuUsuario.this, ActivityCadastrarUsuario.class);
                startActivity(intent);
            }
        });
    }
}
