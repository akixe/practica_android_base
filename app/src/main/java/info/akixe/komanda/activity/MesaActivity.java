package info.akixe.komanda.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import info.akixe.komanda.adapter.PlatosRecyclerViewAdapter;
import info.akixe.komanda.R;
import info.akixe.komanda.model.Alergeno;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.Platos;

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

        // TODO: 8/12/16 Platos de mesa dinámicos
        mMesa.addPlato(Platos.getInstance().getPlato(0));
        mMesa.addPlato(Platos.getInstance().getPlato(1));


        mMesa = (Mesa) getIntent().getSerializableExtra(EXTRA_MESA);
        mPlatosList = (RecyclerView) findViewById(R.id.platos_list);
        mPlatosList.setLayoutManager(new LinearLayoutManager(this));
        mPlatosList.setItemAnimator(new DefaultItemAnimator());
        mPlatosList.setAdapter(new PlatosRecyclerViewAdapter(mMesa.getPlatos(), this));

    }


}
