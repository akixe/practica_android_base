package info.akixe.komanda.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import info.akixe.komanda.fragment.ListaMesasFragment;
import info.akixe.komanda.R;
import info.akixe.komanda.fragment.ListaPlatosFragment;
import info.akixe.komanda.model.Platos;
import info.akixe.komanda.model.singleton.Mesas;

public class ListaMesasActivity extends AppCompatActivity implements ListaMesasFragment.OnMesaSelectedListener {

    private ListaMesasFragment mListaMesasView;
    private int mIndiceMesa;
    private ListaPlatosFragment mListaPlatosView;

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
        mListaMesasView = new ListaMesasFragment();
        if (fm.findFragmentById(R.id.fragment_mesas_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_mesas_list, mListaMesasView)
                    .commit();
        }


        if (findViewById(R.id.fragment_plato_list) != null) {
            // Existe hueco, y habiendo hueco metemos el fragment de CityList
            //mListaPlatosView = ListaPlatosFragment.newInstance(-1, Mesas.getInstance().getMesaAt(mIndiceMesa).getPlatos(), R.layout.row_view_plato_pedido);// Metemos la lista de platos de la mesa
            /*if (fm.findFragmentById(R.id.fragment_plato_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_plato_list, new ListaPlatosFragment())
                        .commit();
            }*/
        }

    }

    @Override
    public void onMesaSelected(int indiceMesa) {

        mIndiceMesa = indiceMesa;

        FragmentManager fm = getFragmentManager();
        ListaPlatosFragment listaPlatosFragment = (ListaPlatosFragment) fm.findFragmentById(R.id.fragment_plato_list);

        Platos platos = Mesas.getInstance().getMesaAt(indiceMesa).getPlatos();
        if (findViewById(R.id.fragment_plato_list) != null) {

            mListaPlatosView = ListaPlatosFragment.newInstance(indiceMesa, Mesas.getInstance().getMesaAt(indiceMesa).getPlatos(), R.layout.row_view_plato_pedido);// Metemos la lista de platos de la mesa
            if (fm.findFragmentById(R.id.fragment_plato_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_plato_list, mListaPlatosView)
                        .commit();
            }


            //listaPlatosFragment.refreshData(platos, indiceMesa);
        } else {
            // Han seleccionado una mesa
            //   Activamos
            Intent intent = new Intent(this, ListaPlatosMesaActivity.class);
            intent.putExtra(ListaPlatosMesaActivity.EXTRA_MESA, indiceMesa);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mListaMesasView.refreshData();
    }
}
