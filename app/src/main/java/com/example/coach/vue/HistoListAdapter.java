package com.example.coach.vue;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends RecyclerView.Adapter<HistoListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Profil> lesProfils;

    /**
     * Constructeur qui valorise les profils et le context
     * @param context
     * @param lesProfils
     */
    public HistoListAdapter(Context context, ArrayList<Profil> lesProfils){
        this.lesProfils = lesProfils;
        this.context = context;

    }
    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public HistoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_liste_histo, parent, false);
        return new HistoListAdapter.ViewHolder(view);
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull HistoListAdapter.ViewHolder holder, int position) {
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure()));
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(position).getImg()));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        return lesProfils.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView txtListDate;
        public final TextView txtListIMG;
        public final ImageButton btnListSuppr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListDate = (TextView) itemView.findViewById(R.id.txtListDate);
            txtListIMG = (TextView) itemView.findViewById(R.id.txtListIMG);
            btnListSuppr = (ImageButton) itemView.findViewById(R.id.btnListSuppr);
            btnListSuppr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Bouton suppr", "**********clic suppr a la ligne : " + getAdapterPosition());
                    Controle controle = Controle.getInstance();
                    controle.delProfil(lesProfils.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });
            txtListDate.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((HistoActivity)context).afficheProfil(lesProfils.get(getAdapterPosition()));
                }
            }));
            txtListIMG.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((HistoActivity)context).afficheProfil(lesProfils.get(getAdapterPosition()));
                }
            }));

        }
    }
}
