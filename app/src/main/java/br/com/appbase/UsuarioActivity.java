package br.com.appbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.appbase.database.AppDataBase;
import br.com.appbase.model.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
    }

    public void onClickVoltar(View v) {finish();}

    public void onClickSalvar(View v){
        //Criar os edits
        EditText editNome = findViewById(R.id.editNome);
        EditText editLogin = findViewById(R.id.editLogin);
        EditText editSenha = findViewById(R.id.editSenha);
        //Pegar os dados dos edits
        String nome = editNome.getText().toString();
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        //Verificar se os campos foram preenchidos
        if (nome.equals("") || login.equals("") || senha.equals("")){
            Toast.makeText(this, "Preenchas todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "appbase_bd")
                            .allowMainThreadQueries()//permitir consultas em threads
                            .build();
                Usuario u = db.usuarioDAO().findByLogin(login);
                //verificar se usuário já existe
                if (u != null){
                    Toast.makeText(this, "O usuário já existe. Tente outro.", Toast.LENGTH_SHORT).show();
                } else {
                    //criar objeto
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setLogin(login);
                    usuario.setSenha(senha);
                    //salvar objeto
                    db.usuarioDAO().insertUsuarios(usuario);
                    Toast.makeText(this, "Usuário salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();//fechar a Ativity
                }
            } catch (Exception e) {
                Toast.makeText(this, "Ocorreu um erro inesperado ao tentar salvar o usuário.",
                        Toast.LENGTH_SHORT).show();
            }

        }

    }


}
