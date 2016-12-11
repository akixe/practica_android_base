package info.akixe.komanda.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.akixe.komanda.R;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.Platos;

/**
 * Created by aki on 7/12/16.
 */

public class PlatoActivity extends AppCompatActivity {
    public static final String EXTRA_PLATO = "EXTRA_PLATO";

    // Modelo
    private Plato mPlato;

    // Vistas
    private TextView mNombre;
    private TextView mNotas;
    private EditText mCantidad;
    private ImageView mAlergenoCascara;
    private ImageView mAlergenoGluten;
    private ImageView mAlergenoHuevo;
    private ImageView mAlergenoLacteos;
    private ImageView mImagen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato);

        // =======
        // Toolbar
        // =======
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // ==================
        // Recuperamos Modelo
        // ==================
        mPlato = (Plato) getIntent().getSerializableExtra(EXTRA_PLATO);
        getSupportActionBar().setTitle(mPlato.getNombre());

        // ==================
        // Recuperamos Vistas
        // ==================
        mImagen = (ImageView) findViewById(R.id.img_foto_plato);
        mNombre = (TextView) findViewById(R.id.txt_nombre_plato);
        mCantidad = (EditText) findViewById(R.id.txt_cantidad);
        mNotas = (EditText) findViewById(R.id.txt_notas_plato);

        // Imagenes alergeno
        mAlergenoCascara = (ImageView) findViewById(R.id.icono_alergeno_frutos_cascara);
        mAlergenoGluten= (ImageView) findViewById(R.id.icono_alergeno_gluten);
        mAlergenoHuevo = (ImageView) findViewById(R.id.icono_alergeno_huevo);
        mAlergenoLacteos = (ImageView) findViewById(R.id.icono_alergeno_lacteos);


        // ==================
        // Modelo --> Vista
        // ==================
        mImagen.setImageResource(mPlato.getRecursoImagen());
        mNombre.setText(mPlato.getNombre());
        mCantidad.setText(Integer.toString(mPlato.getCantidad()));
        mNotas.setText(mPlato.getNotas());


        // Iconos de Alérgenos
        // TODO: 11/12/16 Refactorizar --> Simplificar
        ArrayList<Integer> alergenos = mPlato.getAlergenos();
        if (alergenos.contains(0)) {
            mAlergenoGluten.setVisibility(View.VISIBLE);
        } else {
            mAlergenoGluten.setVisibility(View.GONE);
        }
        if (alergenos.contains(1)) {
            mAlergenoCascara.setVisibility(View.VISIBLE);
        } else {
            mAlergenoCascara.setVisibility(View.GONE);
        }
        if (alergenos.contains(2)) {
            mAlergenoLacteos.setVisibility(View.VISIBLE);
        } else {
            mAlergenoLacteos.setVisibility(View.GONE);
        }
        if (alergenos.contains(3)) {
            mAlergenoHuevo.setVisibility(View.VISIBLE);
        } else {
            mAlergenoHuevo.setVisibility(View.GONE);
        }
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

        if (item.getItemId() == R.id.save_plato) {
            //========
            // Guardar
            //========
            // Vista --> Modelo
            mPlato.setNotas(mNotas.getText().toString());
            mPlato.setCantidad(Integer.parseInt(mCantidad.getText().toString()));

            // Devolvemos el plato
            Intent returnIntent = new Intent(this, ListaPlatosMesaActivity.class);
            returnIntent.putExtra(EXTRA_PLATO, mPlato);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else if (item.getItemId() == R.id.remove_plato) {
            //========
            // Quitar
            //========
            final Intent returnIntent = new Intent(this, ListaPlatosMesaActivity.class);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Quitar plato");
            alertDialog.setMessage("¿Desea quitar el plato?");
            alertDialog.setPositiveButton("Proceder", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mPlato.setCantidad(0);
                    // Devolvemos el plato

                    returnIntent.putExtra(EXTRA_PLATO, mPlato);
                    setResult(RESULT_OK, returnIntent);

                    finish();
                }
            });

            alertDialog.setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    finish();
                }
            });

            alertDialog.show();


        }

        return res;
    }
}
