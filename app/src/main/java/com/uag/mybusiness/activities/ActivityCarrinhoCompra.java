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

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.ProdutoControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterCarrinhoProdutos;

import java.util.List;

public class ActivityCarrinhoCompra extends AppCompatActivity {

    private Button buttonFinalizarCompra;
    private Button buttonBuscarProduto;
    private EditText buscarProduto;
    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterCarrinhoProdutos adapterCarrinhoProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_compra);

        //Produtos do banco

        final ProdutoControle produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityCarrinhoCompra.this));

        this.buscarProduto = (EditText) findViewById(R.id.editTextBuscarProdutoCarrinho);

        produtoList = produtoControle.listarProdutoControle(buscarProduto.getText().toString());

        this.listViewProduto = (ListView) findViewById(R.id.listViewCarrinhoCompra);

        this.adapterCarrinhoProdutos = new AdapterCarrinhoProdutos(ActivityCarrinhoCompra.this, this.produtoList);

        this.listViewProduto.setAdapter(this.adapterCarrinhoProdutos);

        this.buttonFinalizarCompra = (Button) findViewById(R.id.buttonFinalizarCompra);

        this.buttonBuscarProduto = (Button) findViewById(R.id.buttonBuscarProdutoCarrinho);

        this.listViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int posicao, long id) {
                final Produto produtoSelecionado = (Produto) adapterCarrinhoProdutos.getItem(posicao);

                AlertDialog.Builder janelaMenuProduto = new AlertDialog.Builder(ActivityCarrinhoCompra.this);

                janelaMenuProduto.setTitle("Escolha");
                janelaMenuProduto.setMessage("O que deseja fazer com o produto selecionado?");

                janelaMenuProduto.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaMenuProduto.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaMenuProduto.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                janelaMenuProduto.create().show();

            }
        });

        this.buttonBuscarProduto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto

                produtoList = produtoControle.listarProdutoControle(buscarProduto.getText().toString());

                listViewProduto = (ListView) findViewById(R.id.listViewCarrinhoCompra);

                adapterCarrinhoProdutos = new AdapterCarrinhoProdutos(ActivityCarrinhoCompra.this, produtoList);

                listViewProduto.setAdapter(adapterCarrinhoProdutos);


            }
        });

        this.buttonFinalizarCompra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto
                Intent intent = new Intent(ActivityCarrinhoCompra.this, ActivityCadastrarProduto.class);
                startActivity(intent);
            }
        });
    }

}
