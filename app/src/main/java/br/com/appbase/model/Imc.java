package br.com.appbase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity(tableName = "imc",
         foreignKeys = @ForeignKey(entity = Usuario.class,
                 parentColumns = "id",
                 childColumns = "usuarioid",
                 onDelete = ForeignKey.CASCADE))
public class Imc {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "usuarioid")
    private long usuarioId;

    @ColumnInfo(name = "altura")
    private double altura;

    @ColumnInfo(name = "peso")
    private double peso;

    @ColumnInfo(name = "imc")
    private double imc;

    @ColumnInfo(name = "resultado")
    private String resultado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc){
        this.imc = imc;
    }

    public void calcularImc() {

        this.imc = this.peso / (Math.pow(this.altura, 2));

        if (imc < 18.5){
            this.resultado = "Magreza.";
        } else if (imc >= 18.5 && imc < 25.0){
            this.resultado = "Normal.";
        } else if (imc >= 25.0 && imc < 30.0){
            this.resultado = "Sobrepreso. Obesidade I";
        } else if (imc >= 30.0 && imc < 40.0){
            this.resultado = "Obesidade do tipo II.";
        } else {
            this.resultado = "Obesidade Grave, do tipo III";
        }
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "A= " + String.format("%.2f", altura) + " P=" + String.format("%.2f", peso) + "\nIMC= " + String.format("%.2f", imc) + ": " + resultado;
    }
}
