package com.uag.mybusiness.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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
    private Button buttonBuscarProduto;
    private EditText buscarProduto;
    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produto);


        //Produtos do banco

        final ProdutoControle produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityMenuProduto.this));

        this.buscarProduto = (EditText) findViewById(R.id.editTextBuscarProduto);

        produtoList = produtoControle.listarProdutoControle(buscarProduto.getText().toString());

        this.listViewProduto = (ListView) findViewById(R.id.listViewProdutos);

        this.adapterListaProdutos = new AdapterListaProdutos(ActivityMenuProduto.this, this.produtoList);

        this.listViewProduto.setAdapter(this.adapterListaProdutos);

        this.buttonCadastrarProduto = (ImageButton) findViewById(R.id.buttonCadrastarProduto);

        this.buttonBuscarProduto = (Button) findViewById(R.id.buttonBuscarProduto);

        this.listViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int posicao, long id) {
                final Produto produtoSelecionado = (Produto) adapterListaProdutos.getItem(posicao);

                AlertDialog.Builder janelaMenuProduto = new AlertDialog.Builder(ActivityMenuProduto.this);

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
                        boolean excluiu = produtoControle.excluirProdutoControle(produtoSelecionado.getId());

                        if(excluiu == true){
                            adapterListaProdutos.removerProduto(posicao);
                            Toast.makeText(ActivityMenuProduto.this, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(ActivityMenuProduto.this, "Erro ao excluir produto", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                janelaMenuProduto.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundleProduto = new Bundle();

                        bundleProduto.putInt("id", produtoSelecionado.getId());
                        bundleProduto.putString("nome_produto", produtoSelecionado.getNome());
                        bundleProduto.putInt("quantidade", produtoSelecionado.getQuantidade());
                        bundleProduto.putString("data_entrada", produtoSelecionado.getDataEntrada());
                        bundleProduto.putDouble("preco_compra", produtoSelecionado.getPrecoCompra());
                        bundleProduto.putDouble("preco_venda", produtoSelecionado.getPrecoVenda());
                        bundleProduto.putString("descricao", produtoSelecionado.getDescricao());
                        bundleProduto.putString("foto_principal", produtoSelecionado.getFotoPrincipal());
                        bundleProduto.putString("foto_secundaria", produtoSelecionado.getFotoSecundaria());

                        Intent editarProduto = new Intent(ActivityMenuProduto.this, ActivityEditarProduto.class);

                        editarProduto.putExtras(bundleProduto);
                        finish();
                        startActivity(editarProduto);
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

                listViewProduto = (ListView) findViewById(R.id.listViewProdutos);

                adapterListaProdutos = new AdapterListaProdutos(ActivityMenuProduto.this, produtoList);

                listViewProduto.setAdapter(adapterListaProdutos);


            }
        });

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
