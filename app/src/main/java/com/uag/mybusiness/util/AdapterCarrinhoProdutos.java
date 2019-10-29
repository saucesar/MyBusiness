package com.uag.mybusiness.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;

import java.util.List;

public class AdapterCarrinhoProdutos extends BaseAdapter {

    private Context context;;
    private List<Produto> produtoList;

    public AdapterCarrinhoProdutos(Context context, List<Produto> produtoList) {
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

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View view = View.inflate(this.context, R.layout.layout_produto_carrinho, null);

        TextView textViewNomeProduto = (TextView)view.findViewById(R.id.textViewNomeProdutoCarrinho);
        TextView textViewQuantidade = (TextView)view.findViewById(R.id.textViewQuantidadeValorProdutoCarrinho);
        TextView textViewPrecoVenda = (TextView)view.findViewById(R.id.textViewPrecoValorProdutoCarrinho);


        textViewNomeProduto.setText(String.valueOf(this.produtoList.get(posicao).getNome()));
        textViewQuantidade.setText(String.valueOf(this.produtoList.get(posicao).getQuantidade()));
        textViewPrecoVenda.setText(String.valueOf(this.produtoList.get(posicao).getPrecoVenda()));


        return view;
    }

    //atualiza a lista
    public void atualizar(List<Produto> produtos){
        this.produtoList.clear();
        this.produtoList = produtos;
        this.notifyDataSetChanged();
    }

}
