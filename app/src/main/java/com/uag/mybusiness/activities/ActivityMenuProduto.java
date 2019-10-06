package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uag.mybusiness.R;

public class ActivityMenuProduto extends AppCompatActivity {

    private Button buttonCadastrarProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produto);

        this.buttonCadastrarProduto = (Button) findViewById(R.id.buttonCadastrarProduto);

        this.buttonCadastrarProduto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto
                Intent intent = new Intent(ActivityMenuProduto.this, ActivityCadastrarProduto.class);
                startActivity(intent);
            }
        });
    }
}
