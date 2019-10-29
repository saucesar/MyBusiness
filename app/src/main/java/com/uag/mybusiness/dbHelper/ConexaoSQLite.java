package com.uag.mybusiness.dbHelper;

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

        String sqlTabCliente =
                "CREATE TABLE IF NOT EXISTS " +
                        "clientes(id INTEGER PRIMARY KEY autoincrement, nome TEXT, cpf TEXT)";

        String sqlTabEndereco =
                "CREATE TABLE IF NOT EXISTS " +
                        "enderecos(id INTEGER PRIMARY KEY autoincrement, " +
                        "rua TEXT, numero TEXT, bairro TEXT, cidade TEXT, " +
                        "estado TEXT, cpfCliente TEXT)";

        sqLiteDatabase.execSQL(sqLiteTabelaProduto);
        sqLiteDatabase.execSQL(sqlTabCliente);
        sqLiteDatabase.execSQL(sqlTabEndereco);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2){
    }
}
