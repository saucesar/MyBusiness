package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.ProdutoControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterListaProdutos;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityListaProdutos extends AppCompatActivity {

    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    private ProdutoControle produtoControle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        //Produtos do banco

        this.produtoList = new ArrayList<>();

        this.produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityListaProdutos.this));

        produtoList = produtoControle.listarProdutoControle();

        this.listViewProduto = (ListView) findViewById(R.id.listViewProdutos);

        this.adapterListaProdutos = new AdapterListaProdutos(ActivityListaProdutos.this, this.produtoList);

        this.listViewProduto.setAdapter(this.adapterListaProdutos);


    }
}
