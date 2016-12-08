package info.akixe.komanda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import info.akixe.komanda.R;
import info.akixe.komanda.model.Plato;

/**
 * Created by aki on 4/12/16.
 */

public class PlatosRecyclerViewAdapter extends RecyclerView.Adapter<PlatosRecyclerViewAdapter.PlatoViewHolder> {
    private final LinkedList<Plato> mPlatos;
    private final Context mContext;
    private OnPlatoClickListener mOnPlatoClickListener;


    public PlatosRecyclerViewAdapter(LinkedList<Plato> platos, Context context) {
        super();

        mPlatos = platos;
        mContext = context;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_plato, parent, false);
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, final int position) {
        holder.bindPlato(mPlatos.get(position), mContext);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnPlatoClickListener != null) {
                    mOnPlatoClickListener.onPlatoClick(position, mPlatos.get(position), view);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlatos.size();
    }


    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        private final TextView mNombre;
        private final TextView mNotas;
        private final ImageView mAlergenoCascara;
        private final ImageView mAlergenoGluten;
        private final ImageView mAlergenoHuevo;
        private final ImageView mAlergenoLacteos;
        private final ImageView mImagen;


        private View mView;
        public PlatoViewHolder(View itemView) {
            super(itemView);


            mView = itemView;

            // rellenar las vistas
            mNombre = (TextView) itemView.findViewById(R.id.txt_nombre_plato);
            mNotas = (TextView) itemView.findViewById(R.id.txt_notas_plato);
            mAlergenoCascara = (ImageView) itemView.findViewById(R.id.icono_alergeno_frutos_cascara);
            mAlergenoGluten= (ImageView) itemView.findViewById(R.id.icono_alergeno_gluten);
            mAlergenoHuevo = (ImageView) itemView.findViewById(R.id.icono_alergeno_huevo);
            mAlergenoLacteos = (ImageView) itemView.findViewById(R.id.icono_alergeno_lacteos);
            mImagen = (ImageView) itemView.findViewById(R.id.img_plato);

        }

        public void bindPlato(Plato plato, Context context) {
            mNombre.setText(plato.getNombre());
            mNotas.setText(plato.getNotas());
            // TODO: 7/12/16 Añadir imagen a Plato
            // TODO: 7/12/16 comprobar si tiene o no un alérgeno para convertir el icono a gris o dejarlo en color
        }

        public View getView() {
            return mView;
        }
    }

    public interface OnPlatoClickListener {
        public void onPlatoClick(int position, Plato plato, View view);
    }


}
