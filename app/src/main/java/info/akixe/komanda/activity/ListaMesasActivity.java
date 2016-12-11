package info.akixe.komanda.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import info.akixe.komanda.fragment.ListaMesasFragment;
import info.akixe.komanda.R;
import info.akixe.komanda.model.Mesa;

public class ListaMesasActivity extends AppCompatActivity implements ListaMesasFragment.OnMesaSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mesas);

        // =======
        // Toolbar
        // =======
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mesas");


        // ===========
        // Lista Mesas
        // ===========
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_mesas_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_mesas_list, new ListaMesasFragment())
                    .commit();
        }
    }

    @Override
    public void onMesaSelected(int indiceMesa) {
        // Han seleccionado una mesa
        //   Activamos
        Intent intent = new Intent(this, ListaPlatosMesaActivity.class);
        intent.putExtra(ListaPlatosMesaActivity.EXTRA_MESA, indiceMesa);
        startActivity(intent);
    }
}
