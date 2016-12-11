package info.akixe.komanda.model;

import java.io.Serializable;
import java.util.ArrayList;
import info.akixe.komanda.R;

/**
 * Created by aki on 4/12/16.
 */

public class Plato implements Serializable {

    private String mNombre;
    private String mNotas;
    private Boolean mServido;
    private ArrayList<Integer> mAlergenos;
    private int mCantidad;
    private float mPrecioUnitario;
    private int mIndiceImagen;


    public Plato(String nombre, String notas, Boolean servido, ArrayList<Integer> alergenos, float precioUnitario, int indiceImagen) {
        mNombre = nombre;
        mNotas = notas;
        mServido = servido;
        mAlergenos = alergenos;
        mPrecioUnitario = precioUnitario;
        mCantidad = 1;
        mIndiceImagen = indiceImagen;
    }

    public ArrayList<Integer> getAlergenos() {
        return mAlergenos;
    }

    public void setAlergenos(ArrayList<Integer> alergenos) {
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

    public int getIndiceImagen() {
        return mIndiceImagen;
    }

    public void setIndiceImagen(int indiceImagen) {
        mIndiceImagen = indiceImagen;
    }

    //=========
    // METODOS
    //=========
    public float getPrecioTotal() {
        return mPrecioUnitario * mCantidad;
    }


    // Helper para recuperar imagen
    public int getRecursoImagen(){
        int imageResource = 0;
        switch (mIndiceImagen) {
            case 1:
                imageResource = R.drawable.barra_crema;
                break;
            case 2:
                imageResource = R.drawable.brownnie;
                break;
            case 3:
                imageResource = R.drawable.chocolate_cupcake;
                break;
            case 4:
                imageResource = R.drawable.cupcakes;
                break;
            case 5:
                imageResource = R.drawable.cream_puffs;
                break;
            default:
                imageResource = R.drawable.no_foto;
                break;
        }
        return imageResource;
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
