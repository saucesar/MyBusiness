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

                Cliente cliente = getDados();

                if(clienteValido(cliente)){
                    clienteControle.salvarClienteControle(cliente);
                    clikSalvar();
                    Intent intent = new Intent(ActivityCadastrarCliente.this, ActivityMenuCliente.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void clikSalvar(){
        Toast.makeText(ActivityCadastrarCliente.this, "Cliente Salvo com Sucesso!!!", Toast.LENGTH_LONG).show();
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

    private boolean clienteValido(Cliente cliente){
        short len_minimo = 5, len_cpf = 11, len_estado = 2;

        boolean nome_valido = cliente.getNome().length() > len_minimo;
        boolean cpf_valido = cliente.getCpf().length() == len_cpf;
        boolean rua_valida = cliente.getEndereco().getRua().length() > len_minimo;
        boolean numero_valido = cliente.getEndereco().getNumero().length() > 1;
        boolean bairro_valido = cliente.getEndereco().getBairro().length() > len_minimo;
        boolean cidade_valida = cliente.getEndereco().getCidade().length() > len_minimo;
        boolean estado_valido = cliente.getEndereco().getEstado().length() > len_estado;
        boolean cliente_valido = false;

        if(nome_valido && cpf_valido && rua_valida && numero_valido && bairro_valido && cidade_valida && estado_valido){
            cliente_valido = true;
        }
        else{
            String msg = "Campos Abaixo São Invalidos:\n";
            if(!nome_valido){
                msg+="| Nome ";
            }
            if(!cpf_valido){
                msg+="| CPF ";
            }
            if(!rua_valida){
                msg+="| Rua ";
            }
            if(!numero_valido){
                msg+="| Numero ";
            }
            if(!bairro_valido){
                msg+="| Bairro ";
            }
            if(!cidade_valida){
                msg+="| Cidade ";
            }
            if(!estado_valido){
                msg+="| Estado ";
            }
            msg+="|";
            Toast.makeText(ActivityCadastrarCliente.this, msg, Toast.LENGTH_LONG).show();
        }

        return cliente_valido;
    }
}
