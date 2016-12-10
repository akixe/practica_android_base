package info.akixe.komanda.model;

import java.util.ArrayList;

/**
 * Created by aki on 10/12/16.
 */

public class Menu {
    private static Menu mInstance;
    private Platos mPlatos;

    public Menu() {
        mPlatos = new Platos();
        mPlatos.addPlato(new Plato("plato 1", "", false, new ArrayList<Alergeno>()));
        mPlatos.addPlato(new Plato("plato 2", "", false, new ArrayList<Alergeno>()));
        mPlatos.addPlato(new Plato("plato 3", "", false, new ArrayList<Alergeno>()));
    }

    public static Menu getInstance(){
        if(mInstance == null)
        {
            mInstance = new Menu();
        }
        return mInstance;
    }

    public Platos getPlatos(){
        return mPlatos;
    }
}
