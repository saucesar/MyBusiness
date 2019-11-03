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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.ClienteControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Cliente;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterListaClientes;

import java.util.ArrayList;
import java.util.List;

public class ActivityMenuCliente extends AppCompatActivity {

    private ImageButton buttonAddCliente;
    private List<Cliente> clienteList;
    private ListView listViewClientes;
    private AdapterListaClientes adapterListaClientes;

    private EditText editTextNome;
    private EditText editTextCpf;
    private EditText editTextRua;
    private EditText editTextNumero;
    private EditText editTextCidade;
    private EditText editTextBairro;
    private EditText editTextEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        final ClienteControle clienteControle = new ClienteControle(ConexaoSQLite.getInstancia(ActivityMenuCliente.this));
        this.clienteList = clienteControle.listarClienteControle();

        this.adapterListaClientes = new AdapterListaClientes(ActivityMenuCliente.this, this.clienteList);
        this.listViewClientes = (ListView) findViewById(R.id.listViewClientes);
        this.listViewClientes.setAdapter(this.adapterListaClientes);

        this.buttonAddCliente = (ImageButton) findViewById(R.id.buttonAddCliente);
        this.buttonAddCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela cliente
                Intent intent = new Intent(ActivityMenuCliente.this, ActivityCadastrarCliente.class);
                startActivity(intent);
            }
        });

        this.listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int posicao, long id) {
                final Cliente clienteSelecionado = (Cliente) adapterListaClientes.getItem(posicao);

                AlertDialog.Builder janelaMenuCliente = new AlertDialog.Builder(ActivityMenuCliente.this);

                janelaMenuCliente.setTitle("Escolha");
                janelaMenuCliente.setMessage("O que deseja fazer com o Cliente selecionado?");

                janelaMenuCliente.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaMenuCliente.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        boolean excluiu = clienteControle.excluirClienteControle(clienteSelecionado.getCpf());

                        if(excluiu){
                            adapterListaClientes.removerProduto(posicao);
                            Toast.makeText(ActivityMenuCliente.this, "Cliente excluído com sucesso", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(ActivityMenuCliente.this, "Erro ao excluir cliente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                janelaMenuCliente.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundleCliente = new Bundle();

                        bundleCliente.putInt("id", clienteSelecionado.getId());
                        bundleCliente.putString("nome", clienteSelecionado.getNome());
                        bundleCliente.putString("cpf", clienteSelecionado.getCpf());
                        bundleCliente.putString("rua", clienteSelecionado.getEndereco().getRua());
                        bundleCliente.putString("numero", clienteSelecionado.getEndereco().getNumero());
                        bundleCliente.putString("cidade", clienteSelecionado.getEndereco().getCidade());
                        bundleCliente.putString("bairro", clienteSelecionado.getEndereco().getBairro());
                        bundleCliente.putString("estado", clienteSelecionado.getEndereco().getEstado());

                        Intent editarCliente = new Intent(ActivityMenuCliente.this, ActivityCadastrarCliente.class);
                        startActivity(editarCliente);

                        editTextNome = findViewById(R.id.editTextNome);
                        editTextCpf = findViewById(R.id.editTextCpf);
                        editTextRua = findViewById(R.id.editTextRua);
                        editTextNumero = findViewById(R.id.editTextNumero);
                        editTextCidade = findViewById(R.id.editTextCidade);
                        editTextBairro = findViewById(R.id.editTextBairro);
                        editTextEstado = findViewById(R.id.editTextEstado);

                        editTextNome.setText(clienteSelecionado.getNome());
                        editTextCpf.setText(clienteSelecionado.getCpf());
                        editTextRua.setText(clienteSelecionado.getEndereco().getRua());

                        editarCliente.putExtras(bundleCliente);

                        finish();
                    }
                });

                janelaMenuCliente.create().show();
            }
        });
    }
}
