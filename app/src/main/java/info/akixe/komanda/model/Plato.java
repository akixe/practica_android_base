package info.akixe.komanda.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aki on 4/12/16.
 */

public class Plato implements Serializable {

    private String mNombre;
    private String mNotas;
    private Boolean mServido;
    private ArrayList<Alergeno> mAlergenos;
    private int mCantidad;
    private float mPrecioUnitario;


    public Plato(String nombre, String notas, Boolean servido, ArrayList<Alergeno> alergenos, float precioUnitario) {
        mNombre = nombre;
        mNotas = notas;
        mServido = servido;
        mAlergenos = alergenos;
        mPrecioUnitario = precioUnitario;
        mCantidad = 1;
    }

    public ArrayList<Alergeno> getAlergenos() {
        return mAlergenos;
    }

    public void setAlergenos(ArrayList<Alergeno> alergenos) {
        mAlergenos = alergenos;
    }

    public Boolean getServido() {
        return mServido;
    }

    public void setServido(Boolean servido) {
        mServido = servido;
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

    public int getCantidad() {
        return mCantidad;
    }

    public void setCantidad(int cantidad) {
        mCantidad = cantidad;
    }


    public void addCantidad(int cantidad) {
        mCantidad = mCantidad + cantidad;
    }


    public float getPrecioUnitario() {
        return mPrecioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        mPrecioUnitario = precioUnitario;
    }

    //=========
    // METHODS
    //=========
    public float getPrecioTotal() {
        return mPrecioUnitario * mCantidad;
    }



    //============
    // OVERRIDES
    //============
    @Override
    public boolean equals(Object obj) {
            return obj instanceof Plato &&
                    mNombre.equals(((Plato)obj).mNombre);

    }

}
