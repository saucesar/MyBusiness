package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.ProdutoControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Produto;

public class ActivityCadastrarProduto extends AppCompatActivity {

    private EditText editTextIdProduto;
    private EditText editTextNomeProduto;
    private EditText editTextQuantidadeProduto;
    private EditText editTextDataEntradaProduto;
    private EditText editTextPrecoCompra;
    private EditText editTextPrecoVenda;
    private EditText editTextDescricao;
    private CheckBox checkBoxAtivo;
    private Button buttonSalvarProduto;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        editTextNomeProduto = (EditText) findViewById(R.id.editTextNomeProduto);
        editTextQuantidadeProduto = (EditText) findViewById(R.id.editTextQuantidadeProduto);
        editTextDataEntradaProduto = (EditText) findViewById(R.id.editTextDataEntradaProduto);
        editTextPrecoCompra = (EditText) findViewById(R.id.editTextPrecoCompra);
        editTextPrecoVenda = (EditText) findViewById(R.id.editTextPrecoVenda);
        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        checkBoxAtivo = (CheckBox) findViewById(R.id.checkboxAtivo);

        buttonSalvarProduto = (Button) findViewById(R.id.buttonSalvarProduto);
        buttonSalvarProduto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Produto produto = dadosFormularioProduto();

                if(produto != null){
                    ProdutoControle produtoControle = new ProdutoControle(ConexaoSQLite.getInstancia(ActivityCadastrarProduto.this));
                    int codigoProduto = produtoControle.salvarProdutoControle(produto);

                    if(codigoProduto > 0){
                        Toast.makeText(ActivityCadastrarProduto.this, "Produto Salvo com Sucesso!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ActivityCadastrarProduto.this, "Falha ao salvar Produto", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActivityCadastrarProduto.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }

            }

        });

    }

        private Produto dadosFormularioProduto(){

            this.produto = new Produto();

            if(this.editTextNomeProduto.getText().toString().isEmpty() == false){
                this.produto.setNome(this.editTextNomeProduto.getText().toString());
            }else{
                return null;
            }

            if(this.editTextQuantidadeProduto.getText().toString().isEmpty() == false){
                int quantidadeProduto = Integer.parseInt(this.editTextQuantidadeProduto.getText().toString());
                this.produto.setQuantidade(quantidadeProduto);
            }else{
                return null;
            }

            if(this.editTextDataEntradaProduto.getText().toString().isEmpty() == false){
                this.produto.setDataEntrada(this.editTextDataEntradaProduto.getText().toString());
            }else{
                return null;
            }

            if(this.editTextPrecoCompra.getText().toString().isEmpty() == false){
                double precoCompra = Double.parseDouble(this.editTextPrecoCompra.getText().toString());
                this.produto.setPrecoCompra(precoCompra);
            }else{
                return null;
            }

            if(this.editTextPrecoVenda.getText().toString().isEmpty() == false){
                double precoVenda = Double.parseDouble(this.editTextPrecoVenda.getText().toString());
                this.produto.setPrecoVenda(precoVenda);
            }else{
                return null;
            }

            if(this.editTextDescricao.getText().toString().isEmpty() == false){
                this.produto.setDescricao(this.editTextDescricao.getText().toString());
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
}
