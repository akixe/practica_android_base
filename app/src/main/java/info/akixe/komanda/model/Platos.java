package info.akixe.komanda.model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by aki on 4/12/16.
 */

public class Platos {
    private static Platos mInstance = null;
    private LinkedList<Plato> mPlatos;

    public Platos() {
        mPlatos = new LinkedList<>();
        mPlatos.add(new Plato("plato 1", "notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1", false, new ArrayList<Alergeno>()));
        mPlatos.add(new Plato("plato 2", "notas plato 2", false, new ArrayList<Alergeno>()));
        mPlatos.add(new Plato("plato 3", "notas plato 3", false, new ArrayList<Alergeno>()));
    }

    public static Platos getInstance(){
        if(mInstance == null)
        {
            mInstance = new Platos();
        }
        return mInstance;
    }

    public LinkedList<Plato> getPlatos() {
        return mPlatos;
    }

    public void setPlatos(LinkedList<Plato> platos) {
        mPlatos = platos;
    }

    public Plato getPlato(int position){
        if (position > mPlatos.size()-1){
            return null;
        } else {
            return mPlatos.get(position);
        }
    }
}
