package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uag.mybusiness.R;

public class ActivityMenuVenda extends AppCompatActivity {

    private Button buttonNovaVenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_venda);

        this.buttonNovaVenda = (Button) findViewById(R.id.buttonNovaVenda);

        this.buttonNovaVenda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela nova venda
                Intent intent = new Intent(ActivityMenuVenda.this, ActivityNovaVenda.class);
                startActivity(intent);
            }
        });
    }
}
