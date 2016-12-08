package info.akixe.komanda.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.akixe.komanda.fragment.MesaListFragment;
import info.akixe.komanda.R;
import info.akixe.komanda.model.Mesa;

public class MainActivity extends AppCompatActivity implements MesaListFragment.OnMesaSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(R.id.fragment_mesas_list) == null) {
            Log.v("APP", "llega");
            fm.beginTransaction()
                    .add(R.id.fragment_mesas_list, new MesaListFragment())
                    .commit();
        }
    }

    @Override
    public void onMesaSelected(Mesa mesa) {
        // Vamos a comprobar si ya tenemos un pager en nuestra interfaz
            Intent intent = new Intent(this, MesaActivity.class);

            // Le pasamos la ciudad inicial a la actividad siguiente
            intent.putExtra(MesaActivity.EXTRA_MESA, mesa);

            startActivity(intent);
    }
}
