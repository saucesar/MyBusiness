package com.uag.mybusiness.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterProdutosCarrinho;

import java.util.ArrayList;
import java.util.List;

public class ActivityFinalizarCompra extends AppCompatActivity {

    private Button buttonFinalizarCompra;
    private Button buttonBuscarProduto;
    private EditText buscarProduto;
    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterProdutosCarrinho adapterProdutosCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);

        produtoList = (ArrayList<Produto>) getIntent().getSerializableExtra("lista");

        this.listViewProduto = (ListView) findViewById(R.id.listViewProdutosAdicionados);

        this.adapterProdutosCarrinho = new AdapterProdutosCarrinho(ActivityFinalizarCompra.this, this.produtoList);

        this.listViewProduto.setAdapter(this.adapterProdutosCarrinho);

        this.listViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int posicao, long id) {

                AlertDialog.Builder janelaMenuProduto = new AlertDialog.Builder(ActivityFinalizarCompra.this);

                janelaMenuProduto.setTitle("Escolha");
                janelaMenuProduto.setMessage("Deseja remover o produto do carrinho?");

                janelaMenuProduto.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaMenuProduto.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        adapterProdutosCarrinho.removerProduto(posicao);
                    }
                });

                janelaMenuProduto.create().show();

            }
        });

    }
}
