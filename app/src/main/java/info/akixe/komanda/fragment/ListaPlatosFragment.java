package info.akixe.komanda.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.akixe.komanda.R;
import info.akixe.komanda.activity.PlatoActivity;
import info.akixe.komanda.adapter.PlatosRecyclerViewAdapter;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.Platos;
import info.akixe.komanda.model.singleton.Mesas;

/**
 * Created by aki on 8/12/16.
 */

public class ListaPlatosFragment extends Fragment implements PlatosRecyclerViewAdapter.OnPlatoClickListener {
    private static final int REQUEST_PLATO = 1;

    private static final String ARG_PLATOS = "platos";
    private static final String ARG_MESA = "mesa";
    private static final String ARG_ROW_VIEW_TYPE = "row_view_type";
    private Platos mPlatos;
    private RecyclerView mPlatosList;
    private int mRowViewType;
    private int mIndiceMesa;
    private PlatosRecyclerViewAdapter mAdapter;


    public static ListaPlatosFragment newInstance(int indiceMesa, Platos platos, int rowViewType) {
        ListaPlatosFragment fragment = new ListaPlatosFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ARG_MESA, indiceMesa);
        arguments.putSerializable(ARG_PLATOS, platos);
        arguments.putInt(ARG_ROW_VIEW_TYPE, rowViewType);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Saco el modelo de los argumentos
        if (getArguments() != null) {
            mPlatos = (Platos) getArguments().getSerializable(ARG_PLATOS);
            mIndiceMesa = getArguments().getInt(ARG_MESA);
            mRowViewType = getArguments().getInt(ARG_ROW_VIEW_TYPE);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_plato_list, container, false);

        mPlatosList = (RecyclerView) root.findViewById(R.id.platos_list);
        mPlatosList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlatosList.setItemAnimator(new DefaultItemAnimator());
        refreshData();
        return root;
    }

    @Override
    public void onPlatoClick(int position, Plato plato, int indiceMesa, View view) {
        Intent intent = new Intent(getActivity(), PlatoActivity.class);
        Mesa mesa = Mesas.getInstance().getMesaAt(indiceMesa);
        Plato auxPlato;
        // Si la mesa ya había pedido el plato cargamos el plato existente
        if (mesa.getPlatos().contains(plato)) {
            // Es un plato ya agregado al pedido de la mesa
            auxPlato = mesa.getPlato(plato);
        } else {
            // Es un plato nuevo
            auxPlato = plato;
        }
        intent.putExtra(PlatoActivity.EXTRA_PLATO, auxPlato);
        getActivity().startActivityForResult(intent, REQUEST_PLATO);
    }

    public void refreshData() {
        mAdapter = new PlatosRecyclerViewAdapter(mPlatos, mIndiceMesa, getActivity(), this, mRowViewType);
        mPlatosList.setAdapter(mAdapter);
    }
}
