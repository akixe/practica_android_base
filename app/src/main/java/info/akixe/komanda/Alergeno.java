package info.akixe.komanda;

/**
 * Created by aki on 4/12/16.
 */
public class Alergeno {

    private String mNombre;
    private int mIcono;

    public Alergeno(String nombre, int icono) {
        mNombre = nombre;
        mIcono = icono;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public int getIcono() {
        return mIcono;
    }

    public void setIcono(int icono) {
        mIcono = icono;
    }
}