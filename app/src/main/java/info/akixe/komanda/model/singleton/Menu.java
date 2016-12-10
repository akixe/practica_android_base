package info.akixe.komanda.model.singleton;

import java.util.LinkedList;

import info.akixe.komanda.model.Plato;

/**
 * Created by aki on 10/12/16.
 */

public class Menu {
    private static Menu mInstance;
    private LinkedList<Plato> mPlatos;

    public static Menu getInstance(){
        if(mInstance == null)
        {
            mInstance = new Menu();
        }
        return mInstance;
    }

    public LinkedList<Plato> getPlatos(){
        return mPlatos;
    }

    public void setPlatos(LinkedList<Plato> platos) {
        this.mPlatos = platos;
    }
}
