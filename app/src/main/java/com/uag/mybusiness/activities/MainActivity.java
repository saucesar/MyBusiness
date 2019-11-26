package com.uag.mybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uag.mybusiness.R;
import com.uag.mybusiness.controller.UsuarioControle;
import com.uag.mybusiness.dbHelper.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private UsuarioControle userControle;
    private EditText editTextLogin, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pegarElementosDaView();

        this.userControle = new UsuarioControle(ConexaoSQLite.getInstancia(MainActivity.this));
        if(existemUsuarios()){
            Toast.makeText(MainActivity.this,"LOGIN: admin\nSENHA: admin", Toast.LENGTH_LONG).show();
        }else{
            iniciarCadastroUsuario();
        }

        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editTextLogin.getText().toString();
                String senha = editTextSenha.getText().toString();

                if(userControle.autenticar(login,senha)){
                    ActivityMenu.setUser(userControle.buscarPorLogin(login));
                    iniciarMenuPrincipal();
                }
                else{
                    clikFalhaAutenticar();
                }
            }
        });
    }

    private boolean existemUsuarios(){
        return (this.userControle.listarUsuarios().size() > 0);
    }

    private void iniciarCadastroUsuario(){
        Intent intent = new Intent(MainActivity.this, ActivityCadastrarUsuario.class);
        startActivity(intent);
    }

    private void iniciarMenuPrincipal(){
        Intent intent = new Intent(MainActivity.this, ActivityMenu.class);
        startActivity(intent);
        finish();
    }

    private void clikFalhaAutenticar(){
        Toast.makeText(MainActivity.this, "Login e/ou Senha Inválidos!!!", Toast.LENGTH_LONG).show();
    }

    private void pegarElementosDaView(){
        setContentView(R.layout.activity_main);
        this.buttonLogin = findViewById(R.id.buttonLogin);

        this.editTextLogin = findViewById(R.id.editTextLogin);
        this.editTextSenha = findViewById(R.id.editTextSenha);
    }
}
