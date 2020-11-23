package br.com.appbase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.appbase.model.Imc;
import br.com.appbase.model.ImcDAO;
import br.com.appbase.model.Usuario;
import br.com.appbase.model.UsuarioDAO;

@Database(entities = {Usuario.class, Imc.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    //persistência da classe usuário
    public abstract UsuarioDAO usuarioDAO();
    public abstract ImcDAO imcDAO();
}
