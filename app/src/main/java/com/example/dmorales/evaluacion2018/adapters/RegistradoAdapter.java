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
import com.example.dmorales.evaluacion2018.modelo.Registrado;

import java.util.ArrayList;

public class RegistradoAdapter extends RecyclerView.Adapter<RegistradoAdapter.ViewHolder>{
    ArrayList<Registrado> registrados;
    Context context;

    public RegistradoAdapter(ArrayList<Registrado> registrados, Context context) {
        this.registrados = registrados;
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
        Registrado registrado = registrados.get(position);
        holder.txtDni.setText(registrado.getNumdoc());
        holder.txtNombres.setText(registrado.getApepat());
        holder.txtAula.setText(registrado.getAula());
        holder.txtNbungalow.setText(""+registrado.getBungalow());
        if (registrado.getResponsable_bungalow()==1)
           {holder.txtRbungalow.setText("SI");}
           else {holder.txtRbungalow.setText("NO");}
       // holder.txtFecha.setText(checkDigito(registrado.getDia1()) + "-" + checkDigito(registrado.getMes1()) + "-" + (registrado.getAnio1()+1900));
        holder.txtEntrada.setText(checkDigito(registrado.getDia1()) + "-" + checkDigito(registrado.getMes1()) + "-" + (registrado.getAnio1()+1900)+"  "+checkDigito(registrado.getHora1()) + ":" + checkDigito(registrado.getMinuto1()));
        holder.txtSalida.setText(checkDigito(registrado.getDia2()) + "-" + checkDigito(registrado.getMes2()) + "-" + (registrado.getAnio2()+1900)+"  "+checkDigito(registrado.getHora2()) + ":" + checkDigito(registrado.getMinuto2()));

//        if(registrado.getSubido1() == 1){
//            holder.cv.setCardBackgroundColor(Color.GREEN);
//        }else{
//            holder.cv.setCardBackgroundColor(Color.rgb(227,242,253));
//        }
    }

    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public int getItemCount() {
        return registrados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtDni;
        TextView txtNombres;
        TextView txtAula;
        TextView txtNbungalow;
        TextView txtRbungalow;
        TextView txtFecha;
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
            //txtFecha = itemView.findViewById(R.id.item_registrado_txtFecha);
            txtEntrada = itemView.findViewById(R.id.item_registrado_txtHentrada);
            txtSalida = itemView.findViewById(R.id.item_registrado_txtHsalida);
        }
    }
}
