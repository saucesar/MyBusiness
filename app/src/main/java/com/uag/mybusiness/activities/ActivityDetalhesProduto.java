package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.uag.mybusiness.R;
import com.uag.mybusiness.entidades.Produto;
import com.uag.mybusiness.util.AdapterListaProdutos;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityDetalhesProduto extends AppCompatActivity {

    private int idProduto;
    private ImageView fotoPrincipal;
    private ImageView fotoSecundaria;
    private TextView nomeProduto;
    private TextView precoProduto;
    private TextView quantidadeProduto;
    private TextView descricaoProduto;
    private ImageButton buttonFotoAnterior;
    private ImageButton buttonFotoProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        this.nomeProduto = (TextView) findViewById(R.id.textViewDetalhesNome);
        this.precoProduto = (TextView) findViewById(R.id.textViewDetalhesPrecoValor);
        this.quantidadeProduto = (TextView) findViewById(R.id.textViewDetalhesQuantidadeValor);
        this.descricaoProduto = (TextView) findViewById(R.id.textViewDetalhesDescricao);
        this.fotoPrincipal = (ImageView) findViewById(R.id.imageViewDetalhesFoto);
        this.buttonFotoAnterior = (ImageButton) findViewById(R.id.imageButtonAnteriorFoto);
        this.buttonFotoProximo = (ImageButton) findViewById(R.id.imageButtonProximaFoto);

        this.buttonFotoAnterior.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto

               Log.d("Funcionou", ".......................................");


            }
        });

        this.buttonFotoProximo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Chama a janela produto

                Log.d("Funcionou", ".......................................");
                /*//------------Chama a funcao de ler url de imagem e joga essa imagem no XML
                String URL1 = String.valueOf(produto.getFotoPrincipal());
                ImageView imagem1 = (ImageView)findViewById(R.id.imageViewDetalhesFoto);
                imagem1.setImageBitmap(DownloadImage(URL1));

                this.fotoPrincipal.setImageBitmap(DownloadImage(URL1));*/


            }
        });

        Bundle detalhesProduto = getIntent().getExtras();

        Produto produto = new Produto();

        idProduto = detalhesProduto.getInt("id");
        produto.setNome(detalhesProduto.getString("nome_produto"));
        produto.setQuantidade(detalhesProduto.getInt("quantidade"));
        produto.setPrecoVenda(detalhesProduto.getDouble("preco_venda"));
        produto.setDescricao(detalhesProduto.getString("descricao"));
        produto.setFotoPrincipal(detalhesProduto.getString("foto_principal"));
        produto.setFotoSecundaria(detalhesProduto.getString("foto_secundaria"));

        this.setTextViewDetalhesProduto(produto);

    }

    /*
    * Envia os valores para xlm
    *
    * */
   private void setTextViewDetalhesProduto(Produto produto){
        this.nomeProduto.setText(produto.getNome());
        this.precoProduto.setText(String.valueOf(produto.getPrecoVenda()));
        this.quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
        this.descricaoProduto.setText(produto.getDescricao());
       //------------Chama a funcao de ler url de imagem e joga essa imagem no XML
       String URL1 = String.valueOf(produto.getFotoPrincipal());
       ImageView imagem1 = (ImageView)findViewById(R.id.imageViewDetalhesFoto);
       imagem1.setImageBitmap(DownloadImage(URL1));

        this.fotoPrincipal.setImageBitmap(DownloadImage(URL1));

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
