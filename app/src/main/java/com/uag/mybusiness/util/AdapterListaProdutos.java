package com.uag.mybusiness.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class AdapterListaProdutos extends BaseAdapter {

    private Context context;
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
        TextView textViewDescricao = (TextView)view.findViewById(R.id.textViewLayoutProdutoDescricao);
        ImageView ImageViewFotoPrincipal = (ImageView)view.findViewById(R.id.imageViewLayoutFotoPrincipal);
        ImageView ImageViewFotoSecundaria = (ImageView)view.findViewById(R.id.imageViewLayoutFotoSecundaria);


        textViewNomeProduto.setText(String.valueOf(this.produtoList.get(posicao).getNome()));
        textViewQuantidadeProduto.setText(String.valueOf(this.produtoList.get(posicao).getQuantidade()));
        textViewDataEntrada.setText(String.valueOf(this.produtoList.get(posicao).getDataEntrada()));
        textViewPrecoCompra.setText(formatarValorReal(this.produtoList.get(posicao).getPrecoCompra()));
        textViewPrecoVenda.setText(formatarValorReal(this.produtoList.get(posicao).getPrecoVenda()));
        textViewDescricao.setText(String.valueOf(this.produtoList.get(posicao).getDescricao()));

        String URL1 = String.valueOf(this.produtoList.get(posicao).getFotoPrincipal());
        ImageView imagem1 = (ImageView)view.findViewById(R.id.imageViewLayoutFotoPrincipal);
        imagem1.setImageBitmap(DownloadImage(URL1));

        String URL2 = String.valueOf(this.produtoList.get(posicao).getFotoSecundaria());
        ImageView imagem2 = (ImageView)view.findViewById(R.id.imageViewLayoutFotoSecundaria);
        imagem2.setImageBitmap(DownloadImage(URL2));

        return view;
    }

    //atualiza a lista
    public void atualizar(List<Produto> produtos){
        this.produtoList.clear();
        this.produtoList = produtos;
        this.notifyDataSetChanged();
    }

    private String formatarValorReal(double valor){

        double moeda = valor;
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);

        return formatoDois.format(moeda);
    }

    @SuppressLint("NewApi")
    public Bitmap DownloadImage(String pURL){

        StrictMode.ThreadPolicy vPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(vPolicy);

        InputStream inStream = null;
        Bitmap vBitmap = null;

        try{

            URL url = new URL(pURL);
            HttpURLConnection pConnection = (HttpURLConnection)url.openConnection();
            pConnection.setDoInput(true);
            pConnection.connect();

            if(pConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                inStream = pConnection.getInputStream();
                vBitmap = BitmapFactory.decodeStream(inStream);
                inStream.close();

                return vBitmap;
            }

        }
        catch(Exception ex){
            Log.e("Exception",ex.toString());
        }

        return null;

    }



}
