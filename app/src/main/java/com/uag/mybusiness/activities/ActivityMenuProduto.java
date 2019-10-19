package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.uag.mybusiness.controller.ProdutoControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;

import com.uag.mybusiness.R;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterListaProdutos;

import java.util.ArrayList;
import java.util.List;

public class ActivityMenuProduto extends AppCompatActivity {

    private ImageButton buttonCadastrarProduto;
    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    private ProdutoControle produtoControle;

    //ConexaoSQLite conexaoSQLite = ConexaoSQLite.getInstancia(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produto);


        //Produtos do banco

        ProdutoControle produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityMenuProduto.this));

        produtoList = produtoControle.listarProdutoControle();

        this.listViewProduto = (ListView) findViewById(R.id.listViewProdutos);

        this.adapterListaProdutos = new AdapterListaProdutos(ActivityMenuProduto.this, this.produtoList);

        this.listViewProduto.setAdapter(this.adapterListaProdutos);


        this.buttonCadastrarProduto = (ImageButton) findViewById(R.id.buttonCadrastarProduto);


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
