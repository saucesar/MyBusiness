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
import com.uag.mybusiness.entidades.Cliente;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AdapterClienteCarrinho extends BaseAdapter {

    private Context context;;
    private List<Cliente> clienteList;

    public AdapterClienteCarrinho(Context context, List<Cliente> clienteList) {
        this.context = context;
        this.clienteList = clienteList;
    }

    @Override
    public int getCount() {
        return this.clienteList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.clienteList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View view = View.inflate(this.context, R.layout.layout_cliente_carrinho, null);


        //------------Chama a funcao de ler url de imagem e joga essa imagem no XML
        /*String URL1 = String.valueOf(this.clienteList.get(posicao).getFotoPrincipal());
        ImageView imagem1 = (ImageView)view.findViewById(R.id.imageViewProdutoAdicionado);
        imagem1.setImageBitmap(DownloadImage(URL1));*/


        TextView textViewNomeCliente = (TextView)view.findViewById(R.id.textViewNomeClienteEscolhido);



        textViewNomeCliente.setText(String.valueOf(this.clienteList.get(posicao).getNome()));



        return view;
    }

    public void removerProduto(int posicao){
        this.clienteList.remove(posicao);
        notifyDataSetChanged();
    }

    //atualiza a lista
    public void atualizar(List<Cliente> produtos){
        this.clienteList.clear();
        this.clienteList = produtos;
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
