package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.ProdutoControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;

public class ActivityEditarProduto extends AppCompatActivity {


    private int idProduto;
    private EditText edtNome;
    private EditText edtQuantidade;
    private EditText edtDataEntrada;
    private EditText edtPrecoCompra;
    private EditText edtPrecoVenda;
    private EditText edtDescricao;
    private CheckBox checkBoxAtivo;
    private Button buttonAlterar;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        this.edtNome = (EditText)findViewById(R.id.editTextEditNomeProduto);
        this.edtQuantidade = (EditText)findViewById(R.id.editTextEditQuantidadeProduto);
        this.edtDataEntrada = (EditText)findViewById(R.id.editTextEditDataEntradaProduto);
        this.edtPrecoCompra = (EditText)findViewById(R.id.editTextEditPrecoCompra);
        this.edtPrecoVenda = (EditText)findViewById(R.id.editTextEditPrecoVenda);
        this.edtDescricao = (EditText)findViewById(R.id.editTextEditDescricao);
        this.checkBoxAtivo = (CheckBox)findViewById(R.id.checkboxEditAtivo);

        this.buttonAlterar = (Button)findViewById(R.id.buttonSalvarAlteracaoProduto);
        this.clickBotaoSalvarAlteracoes();


        Bundle editarProduto = getIntent().getExtras();

        Produto produto = new Produto();

        idProduto = editarProduto.getInt("id");
        produto.setNome(editarProduto.getString("nome_produto"));
        produto.setQuantidade(editarProduto.getInt("quantidade"));
        produto.setDataEntrada(editarProduto.getString("data_entrada"));
        produto.setPrecoCompra(editarProduto.getDouble("preco_compra"));
        produto.setPrecoVenda(editarProduto.getDouble("preco_venda"));
        produto.setDescricao(editarProduto.getString("descricao"));

        this.setEditTextEditarProduto(produto);
    }

    private void setEditTextEditarProduto(Produto produto){
        this.edtNome.setText(produto.getNome());
        this.edtQuantidade.setText(String.valueOf(produto.getQuantidade()));
        this.edtDataEntrada.setText(produto.getDataEntrada());
        this.edtPrecoCompra.setText(String.valueOf(produto.getPrecoCompra()));
        this.edtPrecoVenda.setText(String.valueOf(produto.getPrecoVenda()));
        this.edtDescricao.setText(produto.getDescricao());
    }

    private Produto dadosFormularioProduto(int idProduto){

        this.produto = new Produto();

        this.produto.setId(idProduto);

        if(this.edtNome.getText().toString().isEmpty() == false){
            this.produto.setNome(this.edtNome.getText().toString());
        }else{
            return null;
        }

        if(this.edtQuantidade.getText().toString().isEmpty() == false){
            int quantidadeProduto = Integer.parseInt(this.edtQuantidade.getText().toString());
            this.produto.setQuantidade(quantidadeProduto);
        }else{
            return null;
        }

        if(this.edtDataEntrada.getText().toString().isEmpty() == false){
            this.produto.setDataEntrada(this.edtDataEntrada.getText().toString());
        }else{
            return null;
        }

        if(this.edtPrecoCompra.getText().toString().isEmpty() == false){
            double precoCompra = Double.parseDouble(this.edtPrecoCompra.getText().toString());
            this.produto.setPrecoCompra(precoCompra);
        }else{
            return null;
        }

        if(this.edtPrecoVenda.getText().toString().isEmpty() == false){
            double precoVenda = Double.parseDouble(this.edtPrecoVenda.getText().toString());
            this.produto.setPrecoVenda(precoVenda);
        }else{
            return null;
        }

        if(this.edtDescricao.getText().toString().isEmpty() == false){
            this.produto.setDescricao(this.edtDescricao.getText().toString());
        }else{
            return null;
        }

        if(checkBoxAtivo.isChecked()){
            this.produto.setAtivo(1);
        }else{
            this.produto.setAtivo(0);
        }

        return produto;
    }

    private void clickBotaoSalvarAlteracoes() {
        this.buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produto = dadosFormularioProduto(idProduto);

                if (produto != null) {
                    ProdutoControle produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityEditarProduto.this));
                    boolean atualizou = produtoControle.atualizarProdutoControle(produto);


                    if (atualizou == true) {
                        finish();
                        Intent intent = new Intent(ActivityEditarProduto.this, ActivityMenuProduto.class);
                        startActivity(intent);
                        Toast.makeText(ActivityEditarProduto.this, "Produto alterado com Sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ActivityEditarProduto.this, "Falha ao alterar Produto", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ActivityEditarProduto.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }

            }

        });
    }


}
