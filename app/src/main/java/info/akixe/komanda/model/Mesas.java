package info.akixe.komanda.model;

import java.util.LinkedList;

/**
 * Created by aki on 3/12/16.
 */

public class Mesas {
    private static Mesas mInstance = null;
    private LinkedList<Mesa> mMesas;

    public Mesas() {
        mMesas = new LinkedList<>();
        mMesas.add(new Mesa("Mesa 1", false));
        mMesas.add(new Mesa("Mesa 2", false));
        mMesas.add(new Mesa("Mesa 3", false));
    }


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
}
