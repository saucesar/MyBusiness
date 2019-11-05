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
    private ClienteControle clienteControle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pegarElementosDaView();

        this.clienteControle = new ClienteControle(ConexaoSQLite.getInstancia(ActivityMenuCliente.this));

        this.clienteList = clienteControle.listarClienteControle();
        this.adapterListaClientes = new AdapterListaClientes(ActivityMenuCliente.this, this.clienteList);
        this.listViewClientes.setAdapter(this.adapterListaClientes);

        this.buttonAddCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela cliente
                iniciarCadastroCliente();
                atualizarListaClientes();
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
                        ActivityCadastrarCliente.setCliente(clienteSelecionado);
                        iniciarCadastroCliente();
                        atualizarListaClientes();
                    }
                });

                janelaMenuCliente.create().show();
            }
        });
    }

    private void atualizarListaClientes(){
        adapterListaClientes.atualizar(this.clienteControle.listarClienteControle());
    }

    private void iniciarCadastroCliente(){
        Intent intent = new Intent(ActivityMenuCliente.this, ActivityCadastrarCliente.class);
        startActivity(intent);
        finish();
    }

    private void pegarElementosDaView(){
        setContentView(R.layout.activity_menu_cliente);
        this.buttonAddCliente = findViewById(R.id.buttonAddCliente);
        this.listViewClientes = findViewById(R.id.listViewClientes);
    }
}
