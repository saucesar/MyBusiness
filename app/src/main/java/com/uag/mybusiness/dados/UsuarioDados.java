package com.uag.mybusiness.dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uag.mybusiness.dbHelper.ConexaoSQLite;
import com.uag.mybusiness.entidades.EnumUser;
import com.uag.mybusiness.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDados implements IDadosUsuario{
    private final ConexaoSQLite conexaoSQLite;
    private final String TABELA_USUARIOS = "usuarios";

    public UsuarioDados(ConexaoSQLite conexaoSQLite){this.conexaoSQLite = conexaoSQLite;}

    public boolean inserir(Usuario user){
        boolean status = false;
        SQLiteDatabase db = null;
        try{
            db = conexaoSQLite.getWritableDatabase();
            ContentValues userValues = new ContentValues();

            userValues.put("login",user.getLogin());
            userValues.put("senha",user.getSenha());
            userValues.put("lembrar",user.getLembrar());
            userValues.put("tipo",user.getTipo().ordinal());

            long result = db.insert(TABELA_USUARIOS,null,userValues);
            status = result > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(db != null){db.close();}
        }
        return status;
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursorUser;

        String queryUsers = "SELECT * FROM "+TABELA_USUARIOS;

        try {
            db = conexaoSQLite.getReadableDatabase();
            cursorUser = db.rawQuery(queryUsers,null);

            while (cursorUser.moveToNext()){

                int id = cursorUser.getInt(0);
                String login  = cursorUser.getString(1);
                String senha  = cursorUser.getString(2);
                boolean lembrar = cursorUser.getInt(3) != 0;
                int tipo = cursorUser.getInt(4);

                usuarios.add(new Usuario(id, login, senha, lembrar, EnumUser.valueOf(tipo)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(db != null){db.close();}
        }

        return usuarios;
    }

    public boolean excluir(int id){
        boolean bool = false;
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {
            db = this.conexaoSQLite.getWritableDatabase();

            db.delete(TABELA_USUARIOS, "if = ?", new String[]{String.valueOf(id)});
            bool = true;
        }
        catch (Exception e){
            Log.d("Erro ao acessar o banco", "Erro ao excluir usuario");
        }finally {
            if(db != null){ db.close(); }
        }
        return bool;
    }

    public boolean atualizar(Usuario user){
        boolean status = false;
        SQLiteDatabase db = null;

        try{
            db = conexaoSQLite.getWritableDatabase();
            ContentValues userValues = new ContentValues();

            userValues.put("login",user.getLogin());
            userValues.put("senha",user.getSenha());
            userValues.put("lembrar",user.getLembrar());
            userValues.put("tipo",user.getTipo().ordinal());

            String whereUser = "id = "+user.getId();

            long result = db.update(TABELA_USUARIOS, userValues, whereUser,null);
            status = result > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(db != null){db.close();}
        }
        return status;
    }

    public boolean autenticar(String login, String senha){
        Usuario user = buscarUsuario(login);
        boolean bool = false;

        if(user != null){ bool = user.autenticar(senha); }
        return bool;
    }

    @Override
    public Usuario buscarUsuario(String login){
        Cursor cursorUser;
        String queryUser = "SELECT * FROM "+TABELA_USUARIOS + " WHERE login = ?";
        SQLiteDatabase db = null;
        Usuario user = null;

        try {
            db = conexaoSQLite.getReadableDatabase();
            cursorUser = db.rawQuery(queryUser, new String[]{String.valueOf(login)});

            while (cursorUser.moveToNext()){

                int id = cursorUser.getInt(0);
                String senha  = cursorUser.getString(2);
                boolean lembrar = cursorUser.getInt(3) != 0;
                int tipo = cursorUser.getInt(4);

                user = new Usuario(id, login, senha, lembrar, EnumUser.valueOf(tipo));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(db != null){db.close();}
        }

        return user;
    }
}
