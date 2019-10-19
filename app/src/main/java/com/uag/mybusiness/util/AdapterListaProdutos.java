package com.uag.mybusiness.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;

import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context context;;
    private List<Produto> produtoList;

    public AdapterListaProdutos(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.produtoList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public void removerProduto(int posicao){
        this.produtoList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View view = View.inflate(this.context, R.layout.layout_produto, null);

        TextView textViewNomeProduto = (TextView)view.findViewById(R.id.textViewLayoutProdutoNome);
        TextView textViewQuantidadeProduto = (TextView)view.findViewById(R.id.textViewLayoutProdutoQuantidade);
        TextView textViewDataEntrada= (TextView)view.findViewById(R.id.textViewLayoutProdutoData);
        TextView textViewPrecoCompra = (TextView)view.findViewById(R.id.textViewLayoutProdutoPrecoCompra);
        TextView textViewPrecoVenda = (TextView)view.findViewById(R.id.textViewLayoutProdutoPrecoVenda);
        TextView textViewDescricao = (TextView)view.findViewById(R.id.textViewLayoutProdutoQuantidade);

        textViewNomeProduto.setText(String.valueOf(this.produtoList.get(posicao).getNome()));
        textViewQuantidadeProduto.setText(String.valueOf(this.produtoList.get(posicao).getQuantidade()));
        textViewDataEntrada.setText(String.valueOf(this.produtoList.get(posicao).getDataEntrada()));
        textViewPrecoCompra.setText(String.valueOf(this.produtoList.get(posicao).getPrecoCompra()));
        textViewPrecoVenda.setText(String.valueOf(this.produtoList.get(posicao).getPrecoVenda()));
        textViewDescricao.setText(String.valueOf(this.produtoList.get(posicao).getDescricao()));

        return view;
    }

    //atualiza a lista
    public void atualizar(List<Produto> produtos){
        this.produtoList.clear();
        this.produtoList = produtos;
        this.notifyDataSetChanged();
    }

}
