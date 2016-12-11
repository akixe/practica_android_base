package info.akixe.komanda.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.text.NumberFormat;
import java.util.Locale;

import info.akixe.komanda.R;
import info.akixe.komanda.fragment.ListaPlatosFragment;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.singleton.Mesas;

/**
 * Created by aki on 4/12/16.
 */

public class ListaPlatosMesaActivity extends AppCompatActivity  {

    private static final int REQUEST_PLATO = 1;
    public static final String EXTRA_MESA = "EXTRA_MESA";
    private Mesa mMesa;
    private ListaPlatosFragment mListaPlatosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos_mesa);

        // =======
        // Toolbar
        // =======
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ======
        // Modelo
        // =======
        final int indiceMesa = getIntent().getIntExtra(EXTRA_MESA, 0);
        mMesa = Mesas.getInstance().getMesaAt(indiceMesa);
        getSupportActionBar().setTitle(getString(R.string.pedido) + mMesa.getNombre());

        // =================
        // Lista Platos Menú
        // =================
        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_plato_list) != null) {
            // Guardamor refrencia a la vista de lista de platos para poder refrescar en el futuro
            mListaPlatosView = ListaPlatosFragment.newInstance(indiceMesa, Mesas.getInstance().getMesaAt(indiceMesa).getPlatos(), R.layout.row_view_plato_pedido);// Metemos la lista de platos de la mesa
            if (fm.findFragmentById(R.id.fragment_plato_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_plato_list, mListaPlatosView)
                        .commit();
            }
        }

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaPlatosMenuActivity.class);
                intent.putExtra(ListaPlatosMenuActivity.EXTRA_MESA, indiceMesa);
                startActivityForResult(intent, REQUEST_PLATO);
            }
        });

        FloatingActionButton cuentaButton = (FloatingActionButton) findViewById(R.id.cuenta_button);
        cuentaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setTitle(R.string.la_cuenta);

                NumberFormat formater = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
                String total =  formater.format(mMesa.getCuentaMesa());

                alertDialog.setMessage(getString(R.string.cuenta_total_label) + " " + total);

                alertDialog.setPositiveButton(R.string.cerrar_cuenta, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mMesa.cerrarCuenta();
                        finish();
                    }
                });

                alertDialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PLATO && resultCode == Activity.RESULT_OK) {
            // Nos devuelven un plato (modificado o nuevo)
            //    a) Lo cargamos a la lista de platos de la mesa
            //    b) Refrescamos la vista de la lista de platos
            Plato plato = (Plato) data.getSerializableExtra(PlatoActivity.EXTRA_PLATO);
            mMesa.addPlato(plato);
            mListaPlatosView.refreshData();
        }
    }



}
