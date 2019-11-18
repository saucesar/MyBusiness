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
import android.widget.TextView;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.CarrinhoControle;
import com.uag.mybusiness.controller.ProdutoControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterCarrinhoProdutos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityCarrinhoCompra extends AppCompatActivity {

    private Button buttonFinalizarCompra;
    private Button buttonBuscarProduto;
    private EditText buscarProduto;
    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterCarrinhoProdutos adapterCarrinhoProdutos;
    private ArrayList<Produto> listProdutoCarrinho;
    private TextView valorTotalItens;
    private double valorCompra;
    private CarrinhoControle carrinhoControle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_compra);

        //Produtos do banco



        final ProdutoControle produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityCarrinhoCompra.this));

        this.listProdutoCarrinho = new ArrayList<>();

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

                janelaMenuProduto.setNegativeButton("Detalhes do Produto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
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

                        Intent detalhesProduto = new Intent(ActivityCarrinhoCompra.this, ActivityDetalhesProduto.class);

                        detalhesProduto.putExtras(bundleProduto);
                        startActivity(detalhesProduto);
                    }
                });

                janelaMenuProduto.setPositiveButton("Adicionar ao Carrinho", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        if (produtoSelecionado.getQuantidade() > 0){
                            AlertDialog.Builder janelaAdicionarProduto = new AlertDialog.Builder(ActivityCarrinhoCompra.this);

                            janelaAdicionarProduto.setTitle(produtoSelecionado.getNome());
                            janelaAdicionarProduto.setMessage("Deseja adicionar ao carrinho");

                            janelaAdicionarProduto.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();
                                }
                            });

                            janelaAdicionarProduto.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    valorTotalCompra(produtoSelecionado);
                                    Toast.makeText(ActivityCarrinhoCompra.this, produtoSelecionado.getNome()+" adicionado ao carrinho",Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }


                            });

                            janelaAdicionarProduto.create().show();
                        }else{
                            AlertDialog.Builder janelaMenuProduto = new AlertDialog.Builder(ActivityCarrinhoCompra.this);

                            janelaMenuProduto.setTitle(produtoSelecionado.getNome());
                            janelaMenuProduto.setMessage("Produto sem estoque");

                            janelaMenuProduto.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                            janelaMenuProduto.create().show();
                        }

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
                /*

                System.out.println(listProdutoCarrinho.size());
                System.out.println(listProdutoCarrinho.get(0).getNome());
                System.out.println(listProdutoCarrinho.get(0).getPrecoVenda());
                System.out.println(listProdutoCarrinho.get(0).getQuantidade());
                System.out.println(listProdutoCarrinho.get(0).getPrecoCompra());
                System.out.println(listProdutoCarrinho.get(0).getFotoPrincipal());
                ArrayList<Produto> lista = new ArrayList<>();
                Produto produto = new Produto();
                lista.add(produto);
                //lista.add(produto);
                //lista.add(produto);
                */
                Intent listaProdutos = new Intent(ActivityCarrinhoCompra.this, ActivityFinalizarCompra.class);
                // passando a lista
                listaProdutos.putExtra("lista",listProdutoCarrinho);
                startActivity(listaProdutos);
                /*
                Intent i = new Intent(ActivityCarrinhoCompra.this, ActivityFinalizarCompra.class);
                i.putExtra("nome", "Elias");
                i.putExtra("sobrenome", "Lima");
                i.putExtra("inteiro", 1299);
                */

                //startActivity(i);
            }
        });
    }

    private void valorTotalCompra(Produto produto){

        this.listProdutoCarrinho.add(produto);
        valorCompra += produto.getPrecoVenda();
        valorTotalItens = (TextView) findViewById(R.id.textViewTotalCompra);
        valorTotalItens.setText(String.valueOf(valorCompra));
    }

}
