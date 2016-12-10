package info.akixe.komanda.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import info.akixe.komanda.R;
import info.akixe.komanda.fragment.ListaPlatosFragment;
import info.akixe.komanda.model.Mesa;

/**
 * Created by aki on 4/12/16.
 */

public class ListaPlatosMesaActivity extends AppCompatActivity  {

    public static final String EXTRA_MESA = "EXTRA_MESA";
    private Mesa mMesa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos_mesa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.fragment_plato_list) != null) {

            mMesa = (Mesa) getIntent().getSerializableExtra(EXTRA_MESA);

            // Metemos la lista de platos de la mesa
            if (fm.findFragmentById(R.id.fragment_plato_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_plato_list, ListaPlatosFragment.newInstance(mMesa, mMesa.getPlatos(), R.layout.row_view_plato_pedido))
                        .commit();
            }
        }

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaPlatosActivity.class);
                intent.putExtra(ListaPlatosActivity.EXTRA_MESA, mMesa);
                startActivity(intent);
            }
        });
    }



}
