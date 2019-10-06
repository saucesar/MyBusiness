package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uag.mybusiness.MainActivity;
import com.uag.mybusiness.R;

public class ActivityMenu extends AppCompatActivity {

    private Button ButtonProduto;
    private Button buttonVenda;
    private Button buttonCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.buttonVenda = (Button) findViewById(R.id.buttonVenda);
        this.ButtonProduto = (Button) findViewById(R.id.buttonProduto);
        this.buttonCliente = (Button) findViewById(R.id.buttonCliente);

        this.ButtonProduto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto
                Intent intent = new Intent(ActivityMenu.this, ActivityMenuProduto.class);
                startActivity(intent);
            }
        });

        this.buttonVenda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela Venda
                Intent intent = new Intent(ActivityMenu.this, ActivityMenuVenda.class);
                startActivity(intent);
            }
        });

        this.buttonCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela menu cliente
                Intent intent = new Intent(ActivityMenu.this, ActivityMenuCliente.class);
                startActivity(intent);
            }
        });
    }
}
