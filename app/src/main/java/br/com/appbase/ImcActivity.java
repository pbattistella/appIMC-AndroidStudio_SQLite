package br.com.appbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

import br.com.appbase.database.AppDataBase;
import br.com.appbase.model.Imc;

public class ImcActivity extends AppCompatActivity {
    private AppDataBase db;

    private long usuarioId;
    public static final String EXTRA_MESSAGE_USUARIOID = "br.com.appimc.EXTRA_MESSAGE_USUARIOID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        Intent i = getIntent();
        usuarioId = i.getLongExtra(MainActivity.EXTRA_MESSAGE_USUARIOID, 1);
        db =  Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "appbase_bd")
                .allowMainThreadQueries()//permitir consultas em threads
                .build();
    }

    public void onCickVoltar(View v){
        abrirMain();
    }

    private void abrirMain() {
        Intent iMain = new Intent(this, MainActivity.class);
        iMain.putExtra(EXTRA_MESSAGE_USUARIOID, usuarioId);
        startActivity(iMain);
        finish();
    }

    public void onClickSalvar(View v){
        EditText editPeso = findViewById(R.id.editPeso);
        EditText editAltura = findViewById(R.id.editAltura);
        EditText editImc = findViewById(R.id.editImc);
        EditText editResultado = findViewById(R.id.editResultado);

        if (editPeso.getText().toString().equals("") || editAltura.getText().toString().equals("")){
            Toast.makeText(this, "Preencha corretamente os campos!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                double peso = Double.parseDouble(editPeso.getText().toString());
                double altura = Double.parseDouble(editAltura.getText().toString());

                Imc imc = new Imc();
                imc.setAltura(altura);
                imc.setPeso(peso);
                imc.calcularImc();
                imc.setUsuarioId(usuarioId);

                db.imcDAO().insertImc(imc);

                String imc_texto = String.format("%.2f", imc.getImc());
                editImc.setText(imc_texto);
                editResultado.setText(imc.getResultado());

                Toast.makeText(this, "Cálculo realizado com sucesso!", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(this, "Ocorreu um erro ao salvar o IMC!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
