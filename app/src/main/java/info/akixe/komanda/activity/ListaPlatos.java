package info.akixe.komanda.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import info.akixe.komanda.R;
import info.akixe.komanda.fragment.ListaPlatosFragment;
import info.akixe.komanda.model.Menu;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.Platos;

/**
 * Created by aki on 9/12/16.
 */

public class ListaPlatos extends AppCompatActivity {

    public static final String EXTRA_MESA = "EXTRA_MESA";
    private Mesa mMesa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_anade_plato);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.fragment_plato_list) != null) {
            // Existe el hueco, y habiendo hueco metemos el fragment del CityPager
            if (fm.findFragmentById(R.id.fragment_plato_list) == null) {

                mMesa = (Mesa) getIntent().getSerializableExtra(EXTRA_MESA);

                fm.beginTransaction()
                        .add(R.id.fragment_plato_list, ListaPlatosFragment.newInstance(mMesa, Menu.getInstance().getPlatos(), R.layout.row_view_plato_carta))
                        .commit();
            }
        }
    }


}
