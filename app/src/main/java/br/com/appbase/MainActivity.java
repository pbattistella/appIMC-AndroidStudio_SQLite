package br.com.appbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.appbase.database.AppDataBase;
import br.com.appbase.model.Imc;

public class MainActivity extends AppCompatActivity {
    private AppDataBase db;

    private List<Imc> imcs;
    private ArrayAdapter<Imc> adapter;
    private ListView lvImc;

    private long usuarioId;
    public static final String EXTRA_MESSAGE_USUARIOID = "br.com.appimc.EXTRA_MESSAGE_USUARIOID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db =  Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "appbase_bd")
                .allowMainThreadQueries()//permitir consultas em threads
                .build();
        Intent i = getIntent();
        usuarioId = i.getLongExtra(LoginActivity.EXTRA_MESSAGE_USUARIOID, 1);

        imcs = db.imcDAO().findByUsuario(usuarioId);
        adapter = new ArrayAdapter<Imc>(this,R.layout.support_simple_spinner_dropdown_item, imcs);
        lvImc = findViewById(R.id.lvImc);
        lvImc.setAdapter(adapter);
        lvImc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                             Toast.makeText(MainActivity.this, imcs.get(position).getResultado(), Toast.LENGTH_SHORT).show();
                                         }
                                     }

        );
    }

    public void onClickNovo(View v){
        Intent iImc = new Intent(this, ImcActivity.class);
        iImc.putExtra(EXTRA_MESSAGE_USUARIOID, usuarioId);
        startActivity(iImc);
        finish();
    }


}
