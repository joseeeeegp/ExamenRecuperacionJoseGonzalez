package com.example.b.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.recyclerview.widget.RecyclerView;

import com.example.b.ConsultaCosaRecycler;
import com.example.b.ConsultarCosaIndividual;
import com.example.b.MainActivity;
import com.example.b.R;
import com.example.b.RegistrarCosa;
import com.example.b.entidades.Cosa;

import java.util.ArrayList;

public class ListaCosaAdapter
        extends RecyclerView.Adapter<ListaCosaAdapter.CosaViewHolder>
        implements View.OnClickListener{

    ArrayList<Cosa> listaCosa;
    private View.OnClickListener listener;

    public ListaCosaAdapter(ArrayList<Cosa> listaCosa) {
        this.listaCosa = listaCosa;
    }

    @Override
    public CosaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cosa, null, false);

        view.setOnClickListener(this);

        return new CosaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CosaViewHolder holder, int position) {
        holder.campoId.setText(listaCosa.get(position).getId().toString());
        holder.campoCosa.setText(listaCosa.get(position).getCosa());
        holder.campoCantidad.setText(listaCosa.get(position).getCantidad());


    }

    @Override
    public int getItemCount() {
        return listaCosa.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }

    }

    public class CosaViewHolder extends RecyclerView.ViewHolder {

        TextView campoId, campoCosa,campoCantidad;


        public CosaViewHolder( View itemView) {
            super(itemView);

           campoId = (TextView) itemView.findViewById(R.id.idcontenidoid);
           campoCosa=(TextView) itemView.findViewById(R.id.campoCosa);
           campoCantidad=(TextView) itemView.findViewById(R.id.idContenidoCantidad);
        }
    }
}
