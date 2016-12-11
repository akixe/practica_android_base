package info.akixe.komanda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import info.akixe.komanda.R;
import info.akixe.komanda.model.Plato;
import info.akixe.komanda.model.Platos;

/**
 * Created by aki on 4/12/16.
 */

public class PlatosRecyclerViewAdapter extends RecyclerView.Adapter<PlatosRecyclerViewAdapter.PlatoViewHolder> {
    private final Platos mPlatos;
    private final Context mContext;
    private final int mIndiceMesa;
    private OnPlatoClickListener mOnPlatoClickListener;
    private int mRowViewType = R.layout.row_view_plato_pedido;


    public PlatosRecyclerViewAdapter(Platos platos, int indiceMesa, Context context, OnPlatoClickListener onPlatoClickListener, int rowViewType) {
        super();
        mRowViewType = rowViewType;
        mPlatos = platos;
        mIndiceMesa = indiceMesa;
        mContext = context;
        mOnPlatoClickListener = onPlatoClickListener;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowViewType, parent, false);
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, final int position) {
        holder.bindPlato(mPlatos.getPlato(position), mContext);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnPlatoClickListener != null) {
                    mOnPlatoClickListener.onPlatoClick(position, mPlatos.getPlato(position), mIndiceMesa, view);
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
        private final TextView mPrecio;
        private final TextView mCantidad;


        private View mView;
        public PlatoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            // rellenar las vistas
            mNombre = (TextView) itemView.findViewById(R.id.txt_nombre_plato);
            mNotas = (TextView) itemView.findViewById(R.id.txt_notas_plato);
            mPrecio = (TextView) itemView.findViewById(R.id.txt_precio_plato);
            mCantidad = (TextView) itemView. findViewById(R.id.txt_cantidad_plato);


            mAlergenoCascara = (ImageView) itemView.findViewById(R.id.icono_alergeno_frutos_cascara);
            mAlergenoGluten= (ImageView) itemView.findViewById(R.id.icono_alergeno_gluten);
            mAlergenoHuevo = (ImageView) itemView.findViewById(R.id.icono_alergeno_huevo);
            mAlergenoLacteos = (ImageView) itemView.findViewById(R.id.icono_alergeno_lacteos);
            mImagen = (ImageView) itemView.findViewById(R.id.img_foto_plato);



        }

        public void bindPlato(Plato plato, Context context) {
            mNombre.setText(plato.getNombre());

            // Algunas vistas de la lista de platos no muestran las notas
            if (mNotas != null) {
                if (plato.getNotas().toString().isEmpty()) {
                    mNotas.setText(R.string.sin_notas);
                } else {
                    mNotas.setText(plato.getNotas());
                }

            }

            if (mCantidad != null) {
                mCantidad.setText(Integer.toString(plato.getCantidad()));
            }

            if (mPrecio != null) {
                NumberFormat formater = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
                mPrecio.setText(formater.format(plato.getPrecioTotal()));
            }

            int indiceImagen = plato.getIndiceImagen();
            int imageResource;
            switch (indiceImagen) {
                case 1:
                    imageResource = R.drawable.barra_crema;
                    break;
                case 2:
                    imageResource = R.drawable.brownnie;
                    break;
                case 3:
                    imageResource = R.drawable.chocolate_cupcake;
                    break;
                case 4:
                    imageResource = R.drawable.cupcakes;
                    break;
                case 5:
                    imageResource = R.drawable.cream_puffs;
                    break;
                default:
                    imageResource = R.drawable.no_foto;
                    break;
            }
            mImagen.setImageResource(imageResource);

            // Iconos de Alérgenos
            ArrayList<Integer> alergenos = plato.getAlergenos();
            if (mAlergenoGluten != null && mAlergenoCascara != null && mAlergenoLacteos != null && mAlergenoHuevo != null){
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
        }
        public View getView() {
            return mView;
        }
    }

    public interface OnPlatoClickListener {
        public void onPlatoClick(int position, Plato plato, int mIndiceMesa, View view);
    }

}
