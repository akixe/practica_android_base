package info.akixe.komanda.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by aki on 4/12/16.
 */

public class Platos implements Serializable {

    private LinkedList<Plato> mPlatos;

    public Platos(){
        mPlatos = new LinkedList<Plato>();
    }

    public Platos(LinkedList<Plato> platos) {
        mPlatos = platos;
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

    public void addPlato(Plato plato) {
        if (mPlatos.contains(plato)) {
            int position = mPlatos.indexOf(plato);

            Plato old_plato = mPlatos.get(position);
            old_plato.setNotas(plato.getNotas());
            old_plato.addCantidad(plato.getCantidad());

        } else {
            mPlatos.add(plato);
        }
    }

    public int size(){
        return mPlatos.size();
    }

    public boolean contains(Plato plato) {
        return mPlatos.contains(plato);
    }

    public int getPosicionPlato(Plato plato) {
        return mPlatos.indexOf(plato);
    }



}
