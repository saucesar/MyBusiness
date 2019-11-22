package com.uag.mybusiness.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.List;

public class AdapterProdutosCarrinho extends BaseAdapter {

    private Context context;;
    private List<Produto> produtoList;

    public AdapterProdutosCarrinho(Context context, List<Produto> produtoList) {
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
        View view = View.inflate(this.context, R.layout.layout_produto_adicionado, null);


        //------------Chama a funcao de ler url de imagem e joga essa imagem no XML
        String URL1 = String.valueOf(this.produtoList.get(posicao).getFotoPrincipal());
        ImageView imagem1 = (ImageView)view.findViewById(R.id.imageViewProdutoAdicionado);
        imagem1.setImageBitmap(DownloadImage(URL1));


        TextView textViewNomeProduto = (TextView)view.findViewById(R.id.textViewNomeProdutoAdicionado);
        TextView textViewQuantidade = (TextView)view.findViewById(R.id.textViewQuantidadeProdutoAdicionado);
        TextView textViewPrecoVenda = (TextView)view.findViewById(R.id.textViewPrecoVendaProdutoAdicionado);


        textViewNomeProduto.setText(String.valueOf(this.produtoList.get(posicao).getNome()));
        textViewQuantidade.setText(String.valueOf(this.produtoList.get(posicao).getQuantidade()));
        textViewPrecoVenda.setText(String.valueOf(this.produtoList.get(posicao).getPrecoVenda()));


        return view;
    }

    public void removerProduto(int posicao){
        this.produtoList.remove(posicao);
        notifyDataSetChanged();
    }

    //atualiza a lista
    public void atualizar(List<Produto> produtos){
        this.produtoList.clear();
        this.produtoList = produtos;
        this.notifyDataSetChanged();
    }


    /*
    *
    * @URL
    * Ler uma url de imagem
    *
    * */
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
