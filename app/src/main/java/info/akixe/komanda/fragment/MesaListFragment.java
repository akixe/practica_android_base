package info.akixe.komanda.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import info.akixe.komanda.R;
import info.akixe.komanda.adapter.PlatosRecyclerViewAdapter;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.Mesas;
import info.akixe.komanda.model.Plato;

/**
 * Created by aki on 3/12/16.
 */

public class MesaListFragment extends Fragment implements PlatosRecyclerViewAdapter.OnPlatoClickListener {

    private OnMesaSelectedListener mOnMesaSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View root = inflater.inflate(R.layout.fragment_mesa_list, container, false);

        // Acceder al ListView
        ListView listView = (ListView) root.findViewById(R.id.mesa_list);


        // Cargar modelo
        final Mesas mesas = new Mesas();

        // Crear adaptador

        ArrayAdapter<Mesa> adapter = new ArrayAdapter<Mesa>(
                getActivity(), android.R.layout.simple_list_item_1,
                mesas.getMesas());

        // Añadir adaptador a listView

        listView.setAdapter(adapter);


        // Le asigno un listener a la lista para enterarme de cuándo se ha pulsado una fila
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Avisamos al listener que el usuario ha pulsado una fila
                if (mOnMesaSelectedListener != null) {
                    mOnMesaSelectedListener.onMesaSelected(mesas.getMesaAt(position));
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof  OnMesaSelectedListener) {
            mOnMesaSelectedListener = (OnMesaSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof  OnMesaSelectedListener) {
            mOnMesaSelectedListener = (OnMesaSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnMesaSelectedListener = null;
    }

    @Override
    public void onPlatoClick(int position, Plato plato, View view) {
        //// TODO: 7/12/16 Implementar correctamente onPlatoClick para abrir detalle
        // Vamos a mostrar la vista de detalle
        //Intent intent = new Intent(getActivity(), DetailActivity.class);
        //intent.putExtra(PlatoDetailActivity.EXTRA_FORECAST, forecast);

//        startActivity(intent,
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        getActivity(), // Contexto
//                        view, // Vista origen común
//                        getString(R.string.transition_to_detail) // El nombre dentro de la vista destino
//                ).toBundle());
    }

    public interface OnMesaSelectedListener {
        void onMesaSelected(Mesa mesa);
    }
}
