package com.example.dmorales.evaluacion2018.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo1;
import com.example.dmorales.evaluacion2018.modelo.Registrado;

import java.util.ArrayList;

public class RegistradoAdapter1 extends RecyclerView.Adapter<RegistradoAdapter1.ViewHolder>{
    ArrayList<AsistenteModelo1> asistentes;
    Context context;

    public RegistradoAdapter1(ArrayList<AsistenteModelo1> asistentes, Context context) {
        this.asistentes = asistentes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registrado,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AsistenteModelo1 asistente = asistentes.get(position);
        holder.txtDni.setText(asistente.get_id());
        holder.txtNombres.setText(asistente.getApepat());
        holder.txtAula.setText(asistente.getAula());
        holder.txtNbungalow.setText(""+asistente.getBungalow());
        if (asistente.getResponsable_bungalow()==1)
           {holder.txtRbungalow.setText("SI");}
           else {holder.txtRbungalow.setText("NO");}
        holder.txtEntrada.setText(checkDigito(asistente.getDia1()) + "-" + checkDigito(asistente.getMes1()) + "-" + (asistente.getAnio1()+1900)+"  "+checkDigito(asistente.getHora1()) + ":" + checkDigito(asistente.getMinuto1()));
        if(asistente.getDia2()==0)
        {holder.txtSalida.setText("00-00-0000  00:00");}
        else
        {holder.txtSalida.setText(checkDigito(asistente.getDia2()) + "-" + checkDigito(asistente.getMes2()) + "-" + (asistente.getAnio2()+1900)+"  "+checkDigito(asistente.getHora2()) + ":" + checkDigito(asistente.getMinuto2()));}

        if(asistente.getSubido1() == 1 && asistente.getSubido2()==0){
            holder.cv.setCardBackgroundColor(Color.rgb(255,223,186));
        }else if (asistente.getSubido1() == 1 && asistente.getSubido2()==1){
            holder.cv.setCardBackgroundColor(Color.rgb(201,242,193));
            } else {holder.cv.setCardBackgroundColor(Color.rgb(251,221,221));}
    }

    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public int getItemCount() {
        return asistentes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtDni;
        TextView txtNombres;
        TextView txtAula;
        TextView txtNbungalow;
        TextView txtRbungalow;
        TextView txtEntrada;
        TextView txtSalida;
        CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.item_registrado_cv);
            txtDni = itemView.findViewById(R.id.item_registrado_txtDni);
            txtNombres = itemView.findViewById(R.id.item_registrado_txtNombres);
            txtAula = itemView.findViewById(R.id.item_registrado_txtAula);
            txtNbungalow =itemView.findViewById(R.id.item_registrado_txtNbungalow);
            txtRbungalow =itemView.findViewById(R.id.item_registrado_txtRbungalow);
            txtEntrada = itemView.findViewById(R.id.item_registrado_txtHentrada);
            txtSalida = itemView.findViewById(R.id.item_registrado_txtHsalida);
        }
    }
}
