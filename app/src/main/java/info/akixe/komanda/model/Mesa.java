package info.akixe.komanda.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by aki on 3/12/16.
 */

public class Mesa implements Serializable {

    private String mNombre;
    private Boolean mOcupada;



    private LinkedList<Plato> mPlatos;

    public Mesa(String nombre, Boolean ocupada) {
        mNombre = nombre;
        mOcupada = ocupada;
        mPlatos = new LinkedList<Plato>(); // Lista vacía de platos
    }


    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public Boolean getOcupada() {
        return mOcupada;
    }

    public void setOcupada(Boolean ocupada) {
        this.mOcupada = ocupada;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    public void addPlato(Plato plato){
        mPlatos.add(plato);
    }

    public LinkedList<Plato> getPlatos() {
        return mPlatos;
    }
}
