package info.akixe.komanda.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import info.akixe.komanda.R;
import info.akixe.komanda.model.Alergeno;
import info.akixe.komanda.model.Mesa;
import info.akixe.komanda.model.singleton.Mesas;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.singleton.Menu;

/**
 * Created by aki on 3/12/16.
 */

public class ListaMesasFragment extends Fragment {

    private OnMesaSelectedListener mOnMesaSelectedListener;
    private Mesas mMesas;
    private ListView mListView;
    private ViewSwitcher mViewSwitcher;

    private static final int LOADING_VIEW_INDEX = 0;
    private static final int MESAS_VIEW_INDEX = 1;
    private Menu mMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View root = inflater.inflate(R.layout.fragment_mesa_list, container, false);

        mMesas = Mesas.getInstance();
        mMenu = Menu.getInstance();


        mViewSwitcher = (ViewSwitcher) root.findViewById(R.id.view_switcher);
        mViewSwitcher.setInAnimation(getActivity(), android.R.anim.fade_in);
        mViewSwitcher.setOutAnimation(getActivity(), android.R.anim.fade_out);

        // Acceder al ListView
        mListView = (ListView) root.findViewById(R.id.mesa_list);

        // Le asigno un listener a la lista para enterarme de cuándo se ha pulsado una fila
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Avisamos al listener que el usuario ha pulsado una fila
                if (mOnMesaSelectedListener != null) {
                    mOnMesaSelectedListener.onMesaSelected(mMesas.getMesaAt(position));
                }
            }
        });

        updateDataModel(mMesas.getMesas(), mMenu.getPlatos());
        return root;
    }


    //=========================
    // ON_MESA_SELECED_LISTENER
    //==========================

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

    public interface OnMesaSelectedListener {
        void onMesaSelected(Mesa mesa);
    }



    //=====================
    // DOWNLOAD MESAS TASK
    //=====================
    private void downloadData() {
        AsyncTask<Void, Integer, JSONObject> mesasDownloader = new AsyncTask<Void, Integer, JSONObject>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // Loading
                mViewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected JSONObject doInBackground(Void... voids) {
                URL url = null;
                InputStream input = null;

                try {
                    url = new URL(String.format("http://www.mocky.io/v2/584c236e120000c719372add"));
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1 && !isCancelled()) {
                        sb.append(new String(data, 0, downloadedBytes));

                        publishProgress((int)(currentBytes * 100) / responseLength);
                    }

                    if (isCancelled()) {
                        return null;
                    }

                    JSONObject jsonRoot = new JSONObject(sb.toString());

                    // Para probar bien el progress bar
                    Thread.sleep(3000);

                    return jsonRoot;

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }


            @Override
            protected void onPostExecute(JSONObject jsonRoot) {
                super.onPostExecute(jsonRoot);

                LinkedList<Mesa> mesas = extractMesas(jsonRoot);
                LinkedList<Plato> menu = extractPlatos(jsonRoot);

                if (mesas != null && menu != null) {
                    updateDataModel(mesas, menu);
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("error");
                    alertDialog.setMessage("No se ha podido descargar los datos");
                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadData();
                        }
                    });
                    alertDialog.show();


                }
            }
        };

        mesasDownloader.execute();
    }

    private LinkedList<Mesa> extractMesas(JSONObject jsonRoot) {
        try {
            JSONArray jsonMesas = jsonRoot.getJSONArray("mesas");

            LinkedList<Mesa> listaMesas = new LinkedList<>();
            for (int i = 0; i < jsonMesas.length(); i++) {
                JSONObject m = jsonMesas.getJSONObject(i);
                String nombre = m.getString("nombre");
                listaMesas.add(new Mesa(nombre, false));
            }
            return listaMesas;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private LinkedList<Plato> extractPlatos(JSONObject jsonRoot) {
        try {
            JSONArray jsonPlatos = jsonRoot.getJSONArray("carta");

            LinkedList<Plato> listaPlatos = new LinkedList<>();

            for (int i = 0; i < jsonPlatos.length(); i++) {
                JSONObject p = jsonPlatos.getJSONObject(i);
                String nombre = p.getString("nombre");
                listaPlatos.add(new Plato(nombre, "", false, new ArrayList<Alergeno>(), 0.0f));
            }
            return listaPlatos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateDataModel(LinkedList<Mesa> mesas, LinkedList<Plato> menu){
            if (mesas == null && menu == null) {
                downloadData();
            } else {
                mViewSwitcher.setDisplayedChild(MESAS_VIEW_INDEX);

                mMesas.setMesas(mesas);
                mMenu.setPlatos(menu);

                ArrayAdapter<Mesa> adapter = new ArrayAdapter<Mesa>(
                        getActivity(), android.R.layout.simple_list_item_1,
                        mMesas.getMesas());

                mListView.setAdapter(adapter);
            }

    }
}
