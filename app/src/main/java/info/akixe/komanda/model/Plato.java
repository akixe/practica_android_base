package info.akixe.komanda.model;

import java.util.ArrayList;

import info.akixe.komanda.model.Alergeno;

/**
 * Created by aki on 4/12/16.
 */

public class Plato {

    private String mNombre;
    private String mNotas;
    private Boolean mServida;
    private ArrayList<Alergeno> mAlergenos;


    public Plato(String nombre, String notas, Boolean servida, ArrayList<Alergeno> alergenos) {
        mNombre = nombre;
        mNotas = notas;
        mServida = servida;
        mAlergenos = alergenos;
    }

    public ArrayList<Alergeno> getAlergenos() {
        return mAlergenos;
    }

    public void setAlergenos(ArrayList<Alergeno> alergenos) {
        mAlergenos = alergenos;
    }

    public Boolean getServida() {
        return mServida;
    }

    public void setServida(Boolean servida) {
        mServida = servida;
    }

    public String getNotas() {
        return mNotas;
    }

    public void setNotas(String notas) {
        mNotas = notas;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public void addAlergeno(Alergeno alergeno) {
        mAlergenos.add(alergeno);
    }
}
