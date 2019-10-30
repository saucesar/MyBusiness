package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.ClienteControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Cliente;
import com.uag.mybusiness.entidades.Endereco;

public class ActivityCadastrarCliente extends AppCompatActivity {

    private Button buttonSalvar;
    private EditText editTextNomeCliente;
    private EditText editTextCpfCliente;
    private EditText editTextRua;
    private EditText editTextNumero;
    private EditText editTextCidade;
    private EditText editTextBairro;
    private EditText editTextEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        editTextNomeCliente = (EditText) findViewById(R.id.editTextNome);
        editTextCpfCliente = (EditText) findViewById(R.id.editTextCpf);
        editTextRua = (EditText) findViewById(R.id.editTextRua);
        editTextNumero = (EditText) findViewById(R.id.editTextNumero);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);


        this.buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        this.buttonSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela cliente
                final ClienteControle clienteControle = new ClienteControle(ConexaoSQLite.getInstancia(ActivityCadastrarCliente.this));

                clienteControle.salvarClienteControle(getDados());

                Intent intent = new Intent(ActivityCadastrarCliente.this, ActivityMenuCliente.class);
                startActivity(intent);
                finish();
                clikSalvar();
            }
        });
    }

    public void clikSalvar(){
        Toast.makeText(ActivityCadastrarCliente.this, "Cliente Salvo com Sucesso!", Toast.LENGTH_LONG).show();
    }

    private Cliente getDados(){
        String nome = editTextNomeCliente.getText().toString();
        String cpf = editTextCpfCliente.getText().toString();
        String rua = editTextCpfCliente.getText().toString();
        String numero = editTextNumero.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String estado = editTextEstado.getText().toString();

        return new Cliente(nome,cpf,new Endereco(rua,numero,bairro,cidade,estado,cpf));
    }
}
