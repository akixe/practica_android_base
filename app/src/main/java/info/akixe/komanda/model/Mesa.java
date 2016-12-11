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

        // Si hay más de un plato la asignado la mesa estará ocupada
        if (mPlatos.size() > 0) {
            mOcupada = true;
        }
    }
    public Plato getPlato(Plato plato) {
        if (mPlatos.contains(plato)) {
            return mPlatos.getPlato(mPlatos.getPosicionPlato(plato));
        } else {
            return null;
        }
    }

    public void cerrarCuenta() {
        mPlatos.getPlatos().clear();
        mOcupada = false;
    }

    //============
    // OVERRIDES
    //============
    @Override
    public String toString() {
        //// TODO: 11/12/16 Mesa ocupada en texto: mostrar icono en vista de mesas
        String ocupada = "";
        if (mOcupada) {
            ocupada = " [Ocupada]";
        } else {
            ocupada = " [Libre]";
        }
        return this.getNombre() + ocupada;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Mesa &&
                mNombre.equals(((Mesa)obj).mNombre);

    }

}
