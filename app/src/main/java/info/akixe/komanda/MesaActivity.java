package info.akixe.komanda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by aki on 4/12/16.
 */

public class MesaActivity extends AppCompatActivity {

    public static final String EXTRA_MESA = "EXTRA_MESA";

    private RecyclerView mPlatosList;
    private Mesa mMesa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);

        mMesa= (Mesa) getIntent().getSerializableExtra(EXTRA_MESA);
        mMesa.addPlato(new Plato("plato 1", "notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1 notas plato 1", false, new ArrayList<Alergeno>()));
        mMesa.addPlato(new Plato("plato 2", "notas plato 2", false, new ArrayList<Alergeno>()));
        mMesa.addPlato(new Plato("plato 3", "notas plato 3", false, new ArrayList<Alergeno>()));

        // TODO: 4/12/16 Obtener la mesa mMesa
        mMesa = (Mesa) getIntent().getSerializableExtra(EXTRA_MESA);

        // TODO: obrener la Recycler
        // mList = (RecyclerView) root.findViewById(android.R.id.list);
        mPlatosList = (RecyclerView) findViewById(R.id.platos_list);

        // TODO: Establecer layout Manager (linear layout)
        // mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlatosList.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Establecer animación
        //  mList.setItemAnimator(new DefaultItemAnimator());
        mPlatosList.setItemAnimator(new DefaultItemAnimator());

        // TODO: 4/12/16 Adaptador (falta implentar adaptador personalizado)
        // mList.setAdapter(new ForecastRecyclerViewAdapter(new LinkedList<Forecast>(), getActivity(), this));
        mPlatosList.setAdapter(new PlatosRecyclerViewAdapter(mMesa.getPlatos(), this));
        // TODO: 4/12/16 Actualizar los datos

    }


}
