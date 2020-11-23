package br.com.appbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.appbase.database.AppDataBase;
import br.com.appbase.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_USUARIOID = "br.com.appimc.EXTRA_MESSAGE_USUARIOID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickCadastro(View v){
        Intent iUsuario = new Intent(this, UsuarioActivity.class);
        startActivity(iUsuario);
    }

    public void onClickEntrar(View v){
        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editSenha = findViewById(R.id.editSenha);
        //Pegar os dados dos edits
        String login = editUsuario.getText().toString();
        String senha = editSenha.getText().toString();
        if (login.equals("") || senha.equals("")){
            Toast.makeText(this, "Preenchas todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "appbase_bd")
                        .allowMainThreadQueries()//permitir consultas em threads
                        .build();
                Usuario usuario = db.usuarioDAO().findByLoginAndSenha(login, senha);
                if (usuario != null){
                    Intent iMain = new Intent(this, MainActivity.class);
                    iMain.putExtra(EXTRA_MESSAGE_USUARIOID, usuario.getId());
                    startActivity(iMain);
                    finish();
                } else {
                    Toast.makeText(this, "Usuário ou senha não encontrado!", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Ocorreu um erro inesperado ao tentar salvar o usuário.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
