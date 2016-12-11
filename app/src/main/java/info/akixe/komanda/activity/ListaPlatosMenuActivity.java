package info.akixe.komanda.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import info.akixe.komanda.R;
import info.akixe.komanda.fragment.ListaPlatosFragment;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.Platos;
import info.akixe.komanda.model.singleton.Menu;

/**
 * Created by aki on 9/12/16.
 */

public class ListaPlatosMenuActivity extends AppCompatActivity {
    private static final int REQUEST_PLATO = 1;
    public static final String EXTRA_MESA = "EXTRA_MESA";
    //private Mesa mMesa;
    private Platos mPlatosMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        // =======
        // Toolbar
        // =======
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_anade_plato);


        // =================
        // Lista Platos Menú
        // =================
        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_plato_list) != null) {
            if (fm.findFragmentById(R.id.fragment_plato_list) == null) {
                int indiceMesa = getIntent().getIntExtra(EXTRA_MESA, 0);
                mPlatosMenu = new Platos(Menu.getInstance().getPlatos());
                fm.beginTransaction()
                        .add(R.id.fragment_plato_list, ListaPlatosFragment.newInstance(indiceMesa, mPlatosMenu, R.layout.row_view_plato_carta))
                        .commit();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PLATO && resultCode == Activity.RESULT_OK) {
            // Han seleccionado un Plato del Menú
            // SE lo devolvemos a la lista de Platos de la Mesa
            Plato plato = (Plato) data.getSerializableExtra(PlatoActivity.EXTRA_PLATO);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(PlatoActivity.EXTRA_PLATO, plato);
            setResult(RESULT_OK, returnIntent);
            finish();

        } else if (resultCode == Activity.RESULT_CANCELED) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

}
