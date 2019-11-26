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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ActivityEditarProduto extends AppCompatActivity {


    private int idProduto;
    private EditText edtNome;
    private EditText edtQuantidade;
    private String dataEntrada;
    private EditText edtPrecoCompra;
    private EditText edtPrecoVenda;
    private EditText edtDescricao;
    private EditText edtFotoPrincipal;
    private EditText edtFotoSecundaria;
    private CheckBox checkBoxAtivo;
    private Button buttonAlterar;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        this.edtNome = (EditText)findViewById(R.id.editTextEditNomeProduto);
        this.edtQuantidade = (EditText)findViewById(R.id.editTextEditQuantidadeProduto);

        this.edtPrecoCompra = (EditText)findViewById(R.id.editTextEditPrecoCompra);
        this.edtPrecoVenda = (EditText)findViewById(R.id.editTextEditPrecoVenda);
        this.edtDescricao = (EditText)findViewById(R.id.editTextEditDescricao);
        this.edtFotoPrincipal = (EditText)findViewById(R.id.editTextEditUrlFotoPrincipal);
        this.edtFotoSecundaria= (EditText)findViewById(R.id.editTextEditUrlFotoSecundaria);
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
        produto.setFotoPrincipal(editarProduto.getString("foto_principal"));
        produto.setFotoSecundaria(editarProduto.getString("foto_secundaria"));


        this.setEditTextEditarProduto(produto);
    }

    private void setEditTextEditarProduto(Produto produto){
        this.edtNome.setText(produto.getNome());
        this.edtQuantidade.setText(String.valueOf(produto.getQuantidade()));
        this.dataEntrada = produto.getDataEntrada();
        this.edtPrecoCompra.setText(formatarValorReal(produto.getPrecoCompra()));
        this.edtPrecoVenda.setText(formatarValorReal(produto.getPrecoVenda()));
        this.edtDescricao.setText(produto.getDescricao());
        this.edtFotoPrincipal.setText(produto.getFotoPrincipal());
        this.edtFotoSecundaria.setText(produto.getFotoSecundaria());
    }

    private Produto dadosFormularioProduto(int idProduto){

        this.produto = new Produto();

        this.produto.setId(idProduto);

        if(this.edtNome.getText().toString().isEmpty() == false  &&(
                this.edtNome.getText().toString().length() > 3
        )){
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

        this.produto.setDataEntrada(this.dataEntrada);

        if(this.edtPrecoCompra.getText().toString().isEmpty() == false){
            double precoCompra = Double.parseDouble(formatarRealDouble(this.edtPrecoCompra.getText().toString()));
            this.produto.setPrecoCompra(precoCompra);
        }else{
            return null;
        }

        if(this.edtPrecoVenda.getText().toString().isEmpty() == false){
            double precoVenda = Double.parseDouble(formatarRealDouble(this.edtPrecoVenda.getText().toString()));
            this.produto.setPrecoVenda(precoVenda);
        }else{
            return null;
        }

        if(this.edtDescricao.getText().toString().isEmpty() == false){
            this.produto.setDescricao(this.edtDescricao.getText().toString());
        }else{
            return null;
        }

        if(this.edtFotoPrincipal.getText().toString().isEmpty() == false){
            this.produto.setFotoPrincipal(this.edtFotoPrincipal.getText().toString());
        }else{
            return null;
        }

        if(this.edtFotoSecundaria.getText().toString().isEmpty() == false){
            this.produto.setFotoSecundaria(this.edtFotoSecundaria.getText().toString());
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

    private String formatarValorReal(double valor){

        double moeda = valor;
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);

        return formatoDois.format(moeda);
    }

    private String formatarRealDouble(String valor){

        if(valor.contains(",")){
            String semPonto = valor.replace(".", "");
            String semVirgula = semPonto.replace(",",".");
            return semVirgula;
        }

        return valor;
    }


}
