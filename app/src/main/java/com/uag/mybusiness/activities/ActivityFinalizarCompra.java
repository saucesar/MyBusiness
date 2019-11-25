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
import com.uag.mybusiness.controller.ClienteControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Carrinho;
import com.uag.mybusiness.entidades.Cliente;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterClienteCarrinho;
import com.uag.mybusiness.util.AdapterProdutosCarrinho;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityFinalizarCompra extends AppCompatActivity {

    private Button buttonBuscarCliente;
    private Button buttonComprar;
    private EditText buscarCliente;
    private TextView valorTotalCompra;
    private ListView listViewProduto;
    private List<Produto> produtoList;
    private AdapterProdutosCarrinho adapterProdutosCarrinho;
    private Carrinho carrinho;

    private List<Cliente> clienteList;
    private ListView listViewClientes;
    private AdapterClienteCarrinho adapterClienteCarrinho;
    private ClienteControle clienteControle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);

        this.buttonBuscarCliente = (Button) findViewById(R.id.buttonBuscarClienteCarrinho);
        this.buttonComprar = (Button) findViewById(R.id.buttonCarrinhoComprar);
        this.valorTotalCompra = (TextView) findViewById(R.id.textViewValorTotalCompra);
        this.buscarCliente = (EditText) findViewById(R.id.editTextBuscarClienteCarrinho);

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

                        // atualiza o valor da compra
                        valorTotalCompra.setText(formatarValorReal(valorCompra(produtoList)));
                    }
                });

                janelaMenuProduto.create().show();

            }
        });

        this.clienteControle = new ClienteControle(ConexaoSQLite.getInstancia(ActivityFinalizarCompra.this));

        this.clienteList = clienteControle.listarClienteControle();
        //this.adapterClienteCarrinho = new AdapterClienteCarrinho(ActivityFinalizarCompra.this, this.clienteList);
        //this.listViewClientes.setAdapter(this.adapterClienteCarrinho);

        // atualiza o valor da compra
        this.valorTotalCompra.setText(formatarValorReal(valorCompra(produtoList)));

        this.buttonComprar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto

                Carrinho carrinhoComprado = new Carrinho();

                /*
                *   salva os valores do carrinho
                *   da a saida do produto do banco
                *   lista de produto
                *   nome do cliente
                *   data da compra
                *   tudo no banco
                */

                Toast.makeText(ActivityFinalizarCompra.this, "Compra Realizada!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityFinalizarCompra.this, ActivityCarrinhoCompra.class);
                finish();
                startActivity(intent);


            }
        });
    }

    public double valorCompra(List<Produto> produtoList){
        double valor = 0;

        for(int i= 0; i < produtoList.size(); i++ ){
            valor += produtoList.get(i).getQuantidade() * produtoList.get(i).getPrecoVenda();
        }
        return valor;
    }

    private String formatarValorReal(double valor){

        double moeda = valor;
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);

        return formatoDois.format(moeda);
    }

}
