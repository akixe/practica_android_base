package info.akixe.komanda.model.singleton;

import java.util.LinkedList;

import info.akixe.komanda.model.Mesa;

/**
 * Created by aki on 3/12/16.
 */

public class Mesas {
    private static Mesas mInstance = null;
    private LinkedList<Mesa> mMesas;

    public static Mesas getInstance(){
        if(mInstance == null)
        {
            mInstance = new Mesas();
        }
        return mInstance;
    }

    public LinkedList<Mesa> getMesas() {
        return mMesas;
    }

    public void setMesas(LinkedList<Mesa> mesas) {
        mMesas = mesas;
    }

    public Mesa getMesaAt(int position) {
        return mMesas.get(position);
    }

    public void updateMesa (Mesa mesa) {
        int indice = mMesas.indexOf(mesa);
        mMesas.set(indice, mesa);
    }
}
