package com.uag.mybusiness.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {
    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 2;
    private static final String NOME_DB = "banco_dados_business";

    public ConexaoSQLite (Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstancia(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        criarTabelaProdutos(sqLiteDatabase);
        criarTabelaClientes(sqLiteDatabase);
        criarTabelaEnderecos(sqLiteDatabase);
        criarTabelaUsuarios(sqLiteDatabase);
        criarTabelaCarrinhos(sqLiteDatabase);
        criarTabelaLigacaoCarrinhoProduto(sqLiteDatabase);
    }

    public void criarTabelaProdutos(SQLiteDatabase sqLiteDatabase){
        String sqLiteTabelaProduto =
                "CREATE TABLE IF NOT EXISTS produtos" +
                        "("+
                        "id INTEGER PRIMARY KEY autoincrement,"+
                        "nome TEXT,"+
                        "quantidade INTEGER,"+
                        "data TEXT,"+
                        "precoCompra REAL,"+
                        "precoVenda REAL,"+
                        "descricao TEXT,"+
                        "foto_principal TEXT,"+
                        "foto_secundaria TEXT,"+
                        "ativo INTEGER"+
                        ")";

        sqLiteDatabase.execSQL(sqLiteTabelaProduto);
    }

    public void criarTabelaClientes(SQLiteDatabase sqLiteDatabase){
        String sqlTabCliente =
                "CREATE TABLE IF NOT EXISTS " +
                        "clientes(id INTEGER PRIMARY KEY autoincrement, nome TEXT, cpf TEXT)";

        sqLiteDatabase.execSQL(sqlTabCliente);
    }
    public void criarTabelaEnderecos(SQLiteDatabase sqLiteDatabase){
        String sqlTabEndereco =
                "CREATE TABLE IF NOT EXISTS " +
                        "enderecos(id INTEGER PRIMARY KEY autoincrement, " +
                        "rua TEXT, numero TEXT, bairro TEXT, cidade TEXT, " +
                        "estado TEXT, cpfCliente TEXT)";

        sqLiteDatabase.execSQL(sqlTabEndereco);
    }

    public void criarTabelaUsuarios(SQLiteDatabase sqLiteDatabase){
        String sqlUsuario =
                "CREATE TABLE IF NOT EXISTS " +
                        "usuarios(id INTEGER PRIMARY KEY autoincrement, " +
                        "login TEXT, senha TEXT, lembrar INTEGER)";

        sqLiteDatabase.execSQL(sqlUsuario);
        criarAdmin(sqLiteDatabase);
    }

    public void criarAdmin(SQLiteDatabase sqLiteDatabase){
        ContentValues adminValues = new ContentValues();
        adminValues.put("login","admin");
        adminValues.put("senha","admin");
        adminValues.put("lembrar",0);

        sqLiteDatabase.insert("usuarios",null,adminValues);
    }

    public void criarTabelaCarrinhos(SQLiteDatabase sqLiteDatabase){
        String sqLiteTabelaCarrinho =
                "CREATE TABLE IF NOT EXISTS carrinhos" +
                        "("+
                        "id INTEGER PRIMARY KEY autoincrement,"+
                        "data TEXT,"+
                        "id_cliente INTEGER,"+
                        "totalCompra REAL"+
                        ")";

        sqLiteDatabase.execSQL(sqLiteTabelaCarrinho);
    }

    public void criarTabelaLigacaoCarrinhoProduto(SQLiteDatabase sqLiteDatabase){
        String sqLiteTabelaCarrinho = "CREATE TABLE IF NOT EXISTS carrinhos_produtos ("+
                                      "id_carrinho INTEGER CONSTRAINT id_carrinho REFERENCES carrinhos(id)," +
                                      "id_produto INTEGER  CONSTRAINT id_produto REFERENCES produtos(id)," +
                                      "precoCompra REAL, precoVenda REAL)";

        sqLiteDatabase.execSQL(sqLiteTabelaCarrinho);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2){
    }
}
