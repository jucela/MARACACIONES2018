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
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo2;

import java.util.ArrayList;

public class RegistradoAdapter2 extends RecyclerView.Adapter<RegistradoAdapter2.ViewHolder>{
    ArrayList<AsistenteModelo2> asistentes;
    Context context;

    public RegistradoAdapter2(ArrayList<AsistenteModelo2> asistentes, Context context) {
        this.asistentes = asistentes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registrado2,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AsistenteModelo2 asistente = asistentes.get(position);
        holder.txtDni.setText(asistente.getNumdoc());
        holder.txtNombres.setText(asistente.getApepat());
        holder.txtAula.setText(asistente.getAula());
        holder.txtEntrada.setText(checkDigito(asistente.getDia1()) + "-" + checkDigito(asistente.getMes1()) + "-" + (asistente.getAnio1()+1900)+"  "+checkDigito(asistente.getHora1()) + ":" + checkDigito(asistente.getMinuto1()));
        if (asistente.getSubido1() == 1){
            holder.cv.setCardBackgroundColor(Color.rgb(201,242,193));//verde
            } else {holder.cv.setCardBackgroundColor(Color.rgb(251,221,221));}//rojo
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
        TextView txtEntrada;
        CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.item_registrado_cv2);
            txtDni = itemView.findViewById(R.id.item_registrado_txtDni2);
            txtNombres = itemView.findViewById(R.id.item_registrado_txtNombres2);
            txtAula = itemView.findViewById(R.id.item_registrado_txtAula2);
            txtEntrada = itemView.findViewById(R.id.item_registrado_txtHentrada2);
        }
    }
}
