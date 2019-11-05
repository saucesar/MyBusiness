package com.uag.mybusiness.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Cliente;

import java.util.List;

public class AdapterListaClientes extends BaseAdapter {
    private Context context;
    private List<Cliente> clientesList;

    public AdapterListaClientes(Context context, List<Cliente> clientesList) {
        this.context = context;
        this.clientesList = clientesList;
    }

    @Override
    public int getCount() {
        return this.clientesList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.clientesList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public void removerProduto(int posicao){
        this.clientesList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View view = View.inflate(this.context, R.layout.layout_cliente, null);

        TextView textViewIdCliente = view.findViewById(R.id.textViewLayoutClienteId);
        TextView textViewNomeCliente = view.findViewById(R.id.textViewLayoutClienteNome);
        TextView textViewCpfCliente = view.findViewById(R.id.textViewLayoutClienteCpf);
        TextView textViewRua = view.findViewById(R.id.textViewLayoutClienteRua);
        TextView textViewNumero = view.findViewById(R.id.textViewLayoutClienteNumero);
        TextView textViewBairro = view.findViewById(R.id.textViewLayoutClienteBairro);
        TextView textViewCidade = view.findViewById(R.id.textViewLayoutClienteCidade);
        TextView textViewEstado = view.findViewById(R.id.textViewLayoutClienteEstado);

        textViewIdCliente.setText(String.valueOf(this.clientesList.get(posicao).getId()));
        textViewNomeCliente.setText(String.valueOf(this.clientesList.get(posicao).getNome()));
        textViewCpfCliente.setText(String.valueOf(this.clientesList.get(posicao).getCpf()));
        textViewRua.setText(String.valueOf(this.clientesList.get(posicao).getEndereco().getRua()));
        textViewNumero.setText(String.valueOf(this.clientesList.get(posicao).getEndereco().getNumero()));
        textViewBairro.setText(String.valueOf(this.clientesList.get(posicao).getEndereco().getBairro()));
        textViewCidade.setText(String.valueOf(this.clientesList.get(posicao).getEndereco().getCidade()));
        textViewEstado.setText(String.valueOf(this.clientesList.get(posicao).getEndereco().getEstado()));

        return view;
    }

    //atualiza a lista
    public void atualizar(List<Cliente> clientes){
        this.clientesList.clear();
        this.clientesList = clientes;
        this.notifyDataSetChanged();
    }
}
