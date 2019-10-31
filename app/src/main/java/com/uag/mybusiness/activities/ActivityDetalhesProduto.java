package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;

public class ActivityDetalhesProduto extends AppCompatActivity {

    private int idProduto;
    private ImageView fotoPrincipal;
    private ImageView fotoSecundaria;
    private TextView nomeProduto;
    private TextView precoProduto;
    private TextView quantidadeProduto;
    private TextView descricaoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        this.nomeProduto = (TextView) findViewById(R.id.textViewDetalhesNome);
        this.precoProduto = (TextView) findViewById(R.id.textViewDetalhesPrecoValor);
        this.quantidadeProduto = (TextView) findViewById(R.id.textViewDetalhesQuantidadeValor);
        this.descricaoProduto = (TextView) findViewById(R.id.textViewDetalhesDescricao);

        Bundle detalhesProduto = getIntent().getExtras();

        Produto produto = new Produto();

        idProduto = detalhesProduto.getInt("id");
        produto.setNome(detalhesProduto.getString("nome_produto"));
        produto.setQuantidade(detalhesProduto.getInt("quantidade"));
        produto.setPrecoVenda(detalhesProduto.getDouble("preco_venda"));
        produto.setDescricao(detalhesProduto.getString("descricao"));
        produto.setFotoPrincipal(detalhesProduto.getString("foto_principal"));
        produto.setFotoSecundaria(detalhesProduto.getString("foto_secundaria"));

        this.setEditTextEditarProduto(produto);

    }

    /*
    * Envia os valores para xlm
    *
    * */
    private void setEditTextEditarProduto(Produto produto){
        this.nomeProduto.setText(produto.getNome());
        this.precoProduto.setText(String.valueOf(produto.getPrecoVenda()));
        this.quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
        this.descricaoProduto.setText(produto.getDescricao());

    }
}
