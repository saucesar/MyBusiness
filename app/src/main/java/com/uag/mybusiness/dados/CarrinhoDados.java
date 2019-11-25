package com.uag.mybusiness.dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.Carrinho;
import com.uag.mybusiness.entidades.Cliente;
import com.uag.mybusiness.entidades.Endereco;
import com.uag.mybusiness.entidades.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDados implements IDadosCarrinho{
    private ConexaoSQLite conexaoSQLite;
    public CarrinhoDados(ConexaoSQLite conexaoSQLite){
        this.conexaoSQLite = conexaoSQLite;
    }

    public int inserir(Carrinho carrinho){
        ContentValues CarrinhoValues = new ContentValues();


        CarrinhoValues.put("data",carrinho.getData());
        CarrinhoValues.put("id_cliente",carrinho.getIdCliente());
        CarrinhoValues.put("totalCompra",carrinho.getTotalCompra());

        SQLiteDatabase db = this.conexaoSQLite.getWritableDatabase();
        int status = (int)db.insert("carrinhos",null, CarrinhoValues);

        String selectMaxId = "SELECT MAX(id) FROM carrinhos";
        Cursor c = db.rawQuery(selectMaxId, null);
        c.moveToNext();
        int idCarrinho = c.getInt(0);

        ContentValues produtoValues;

        for(Produto produto:carrinho.getListaProdutos()){
            produtoValues = new ContentValues();
            produtoValues.put("id_carrinho", idCarrinho);
            produtoValues.put("id_produto", produto.getId());
            produtoValues.put("precoCompra", produto.getPrecoCompra());
            produtoValues.put("precoVenda", produto.getPrecoVenda());
            db.insert("carrinhos_produtos",null, produtoValues);
        }

        return status;
    }

    public List<Carrinho> listarCarrinho(){

        List<Carrinho> listaCarrinho = new ArrayList<>();

        SQLiteDatabase db = null;
        String queryCarrinho = "SELECT * from carrinhos";
        String queryCarrinhoProduto = "SELECT p.id, p.nome, p.quantidade, p.data, p.precoCompra, p.precoVenda, p.descricao, " +
                                      "p.foto_principal, p.foto_secundaria, p.ativo, c.id, c.nome, c.cpf, " +
                                      "e.id, e.rua, e.numero, e.bairro, e.cidade, e.estado " +
                                      "FROM carrinhos_produtos cp, produtos p, clientes c, enderecos e " +
                                      "WHERE cp.id_carrinho = ? and cp.id_produto = p.id " +
                                      "and cp.id_cliente = c.cliente and e.cpfCliente = c.cpf";

        Cursor cursorCarrinho, cursorCarrinhoProduto;
        try{
            db = this.conexaoSQLite.getReadableDatabase();
            cursorCarrinho = db.rawQuery(queryCarrinho, null);
            while (cursorCarrinho.moveToNext()){
                Carrinho carrinho = new Carrinho();
                carrinho.setId(cursorCarrinho.getInt(0));

                cursorCarrinhoProduto = db.rawQuery(queryCarrinhoProduto,new String[]{String.valueOf(carrinho.getId())});

                if (cursorCarrinhoProduto.moveToNext()){
                    int idCliente = cursorCarrinhoProduto.getInt(10);
                    String nomeCliente = cursorCarrinhoProduto.getString(11);
                    String cpfCliente = cursorCarrinhoProduto.getString(12);

                    String rua = cursorCarrinhoProduto.getString(13);
                    String numero = cursorCarrinhoProduto.getString(14);
                    String bairro = cursorCarrinhoProduto.getString(15);
                    String cidade = cursorCarrinhoProduto.getString(16);
                    String estado = cursorCarrinhoProduto.getString(17);

                    Endereco e = new Endereco(rua, numero, bairro, cidade, estado, cpfCliente);
                    Cliente c = new Cliente(idCliente, nomeCliente,cpfCliente, e);

                    carrinho.setCliente(c);
                }

                while (cursorCarrinhoProduto.moveToNext()){

                    int idProd = cursorCarrinhoProduto.getInt(0);
                    String nomeProd = cursorCarrinhoProduto.getString(1);
                    int quantProd = cursorCarrinhoProduto.getInt(2);
                    String data = cursorCarrinhoProduto.getString(3);
                    double pcompra = cursorCarrinhoProduto.getDouble(4);
                    double pvenda = cursorCarrinhoProduto.getDouble(5);
                    String descrProd = cursorCarrinhoProduto.getString(6);
                    String fotoPrinc = cursorCarrinhoProduto.getString(7);
                    String fotoSec = cursorCarrinhoProduto.getString(8);
                    int ativo = cursorCarrinhoProduto.getInt(9);

                    Produto p = new Produto(idProd, nomeProd, quantProd, data, pcompra, pvenda, descrProd, fotoPrinc, fotoSec, ativo);
                    carrinho.adicionarProdutoCarrinho(p);
                }
                listaCarrinho.add(carrinho);
            }

        }catch (Exception e){e.printStackTrace();}

        return listaCarrinho;
    }

    public boolean excluir(int idCarrinho) {
        SQLiteDatabase db = null;

        try {
            db = this.conexaoSQLite.getWritableDatabase();

            db.delete("carrinhos",
                    "id = ?",
                    new String[]{String.valueOf(idCarrinho)});

            db.delete("carrinhos_produtos",
                    "id_carrinho = ?",
                    new String[]{String.valueOf(idCarrinho)});
        }
        catch (Exception e){
            Log.d("Erro ao acessar o banco", "Erro ao acessar produtos no banco para excluir");
            return false;
        }finally {
            if(db != null){
                db.close();
            }
        }

        return true;
    }

    public boolean atualizar(Carrinho carrinho){
        this.excluir(carrinho.getId());
        this.inserir(carrinho);
        return true;
    }
}
