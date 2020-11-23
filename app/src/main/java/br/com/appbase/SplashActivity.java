package br.com.appbase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private static final int TEMPO_PARA_ABRIR_LOGIN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        abrirLogin();
                    }
                }, TEMPO_PARA_ABRIR_LOGIN
        );
    }
    //Abrir tela de Login após 2000 milissegundos
    private void abrirLogin(){
        Intent iLogin = new Intent(this, LoginActivity.class);
        startActivity(iLogin);
        finish();//fechar tela de slpash
    }
}
