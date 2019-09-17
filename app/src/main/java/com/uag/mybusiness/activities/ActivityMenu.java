package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uag.mybusiness.MainActivity;
import com.uag.mybusiness.R;

public class ActivityMenu extends AppCompatActivity {

    private Button buttonProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        this.buttonProduto = (Button) findViewById(R.id.buttonProduto);

        this.buttonProduto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto
                Intent intent = new Intent(ActivityMenu.this, ActivityProduto.class);
                startActivity(intent);
            }
        });


    }
}
