package com.tobatani.del.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tobatani.del.R;
import com.tobatani.del.model.TanahItem;
import com.tobatani.del.ui.detail.tanah.DetailTanahActivity;

import java.util.ArrayList;

public class TanahAdapter extends RecyclerView.Adapter<TanahAdapter.TanahViewHolder> {
    private ArrayList<TanahItem> listTanah;
    Context context;

    public TanahAdapter(ArrayList<TanahItem> listTanah, Context context) {
        this.listTanah = listTanah;
        this.context = context;
    }

    public TanahAdapter() {

    }

    @NonNull
    @Override
    public TanahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tanah, parent, false);
        return new TanahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TanahViewHolder holder, final int position) {
        TanahItem tanahItem = listTanah.get(position);
        holder.nameTanah.setText(tanahItem.getNameTanah());
        Glide.with(holder.itemView.getContext())
                .load(tanahItem.getImgTanah())
                .into(holder.imgTanah);
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailTanahActivity.class);
            intent.putExtra("TITLE", listTanah.get(position).getNameTanah());
            intent.putExtra("DESCRIPTION", listTanah.get(position).getDeskripsiTanah());
            intent.putExtra("TANAMAN", listTanah.get(position).getDataTanaman());
            intent.putExtra("WILAYAH", listTanah.get(position).getDataWilayah());
            intent.putExtra("IMAGE", listTanah.get(position).getImgTanah());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listTanah.size();
    }

    class TanahViewHolder extends RecyclerView.ViewHolder {
        //        TanahItem intentTanah;
        TextView nameTanah;
        ImageView imgTanah;
        //        Context context;
        CardView cardView;

        TanahViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
//            context = itemView.getContext();
            nameTanah = itemView.findViewById(R.id.txtTanah);
            imgTanah = itemView.findViewById(R.id.imgTanah);
        }
    }
}
