package info.akixe.komanda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import info.akixe.komanda.R;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.Plato;

/**
 * Created by aki on 7/12/16.
 */

public class PlatoActivity extends AppCompatActivity {
    public static final String EXTRA_PLATO = "EXTRA_PLATO";
    public static final String EXTRA_MESA = "EXTRA_MESA";
    private Plato mPlato;
    private Mesa mMesa;
    private TextView mNombre;
    private TextView mNotas;
    private NumberPicker mCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mNombre = (TextView) findViewById(R.id.txt_nombre_plato);
        mNotas = (EditText) findViewById(R.id.txt_notas_plato);

        mCantidad = (NumberPicker) findViewById(R.id.number_cantidad);
        mCantidad.setMinValue(1);
        mCantidad.setMaxValue(99);
        mCantidad.setWrapSelectorWheel(true);

        mPlato = (Plato) getIntent().getSerializableExtra(EXTRA_PLATO);
        mMesa = (Mesa) getIntent().getSerializableExtra(EXTRA_MESA);

        mNombre.setText(mPlato.getNombre());
        mNotas.setText(mPlato.getNotas());
        mCantidad.setValue(mPlato.getCantidad());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean res = super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_plato, menu);
        return res;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean res = super.onOptionsItemSelected(item);

        mPlato.setNotas(mNotas.getText().toString());
        mPlato.setCantidad(mCantidad.getValue());


        if (item.getItemId() == R.id.save_plato) {
            mMesa.addPlato(mPlato);

            // Volvemos a la pantalla del plato
            Intent intent = new Intent(this, ListaPlatosMesaActivity.class);
            intent.putExtra(ListaPlatosMesaActivity.EXTRA_MESA, mMesa);
            startActivity(intent);

            return true;
        }

        return res;
    }
}
