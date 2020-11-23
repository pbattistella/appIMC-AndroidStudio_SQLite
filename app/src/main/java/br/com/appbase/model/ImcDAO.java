package br.com.appbase.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImcDAO {
    @Query("SELECT * FROM imc")
    public List<Imc> findByAll();

    @Query("SELECT * FROM imc WHERE usuarioid = :id")
    public List<Imc> findByUsuario(long id);

    @Insert
    public void insertImc(Imc ... imcs);

    @Update
    public void updateImc(Imc ... imcs);

    @Delete
    public void deleteImc(Imc ... imcs);

}
