package info.akixe.komanda.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by aki on 3/12/16.
 */

public class Mesa implements Serializable {

    private String mNombre;
    private Boolean mOcupada;



    private Platos mPlatos;

    public Mesa(String nombre, Boolean ocupada) {
        mNombre = nombre;
        mOcupada = ocupada;
        mPlatos = new Platos(); // Lista vacía de platos
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

    public Platos getPlatos() {
        return mPlatos;
    }

    public void setPlatos(Platos platos) {
        this.mPlatos = platos;
    }

    public void addPlato(Plato plato) {
        if (mPlatos.contains(plato)){
            mPlatos.updatePlato(plato);
        } else {
            mPlatos.addPlato(plato);
        }
    }
    public Plato getPlato(Plato plato) {
        if (mPlatos.contains(plato)) {
            return mPlatos.getPlato(mPlatos.getPosicionPlato(plato));
        } else {
            return null;
        }
    }

    //============
    // OVERRIDES
    //============
    @Override
    public String toString() {
        return this.getNombre();
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Mesa &&
                mNombre.equals(((Mesa)obj).mNombre);

    }

}
