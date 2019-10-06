package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uag.mybusiness.R;

public class ActivityMenuCliente extends AppCompatActivity {

    private Button buttonAddCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        this.buttonAddCliente = (Button) findViewById(R.id.buttonAddCliente);
        this.buttonAddCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela cliente
                Intent intent = new Intent(ActivityMenuCliente.this, ActivityCadastrarCliente.class);
                startActivity(intent);
            }
        });
    }
}
