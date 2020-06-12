package com.tobatani.del.ui.penyakit;

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
import com.tobatani.del.model.PenyakitItem;
import com.tobatani.del.ui.detail.penyakit.DetailPenyakitActivity;

import java.util.ArrayList;

public class PenyakitAdapter extends RecyclerView.Adapter<PenyakitAdapter.PenyakitViewHolder> {
    private ArrayList<PenyakitItem> listPenyakit;
    Context context;

    public PenyakitAdapter(ArrayList<PenyakitItem> listPenyakit, Context context) {
        this.listPenyakit = listPenyakit;
        this.context = context;
    }

    @NonNull
    @Override
    public PenyakitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penyakit, parent, false);
        return new PenyakitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenyakitViewHolder holder, final int position) {
        final PenyakitItem penyakitItem = listPenyakit.get(position);
        holder.namaPenyakit.setText(penyakitItem.getNamaPenyakit());
        Glide.with(holder.itemView.getContext())
                .load(penyakitItem.getImgPenyakit())
                .into(holder.imgPenyakit);
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", listPenyakit.get(position).getNamaPenyakit());
            intent.putExtra("LATIN", listPenyakit.get(position).getNamaLatin());
            intent.putExtra("DESKRIPSI", listPenyakit.get(position).getDeskripsiPenyakit());
            intent.putExtra("CIRI", listPenyakit.get(position).getCiriPenyakit());
            intent.putExtra("SOLUSI", listPenyakit.get(position).getSolusiPenyakit());
            intent.putExtra("TANAMAN", listPenyakit.get(position).getSerangTanamanPenyakit());
            intent.putExtra("IMAGE", listPenyakit.get(position).getImgPenyakit());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listPenyakit.size();
    }


    public class PenyakitViewHolder extends RecyclerView.ViewHolder {
        TextView namaPenyakit;
        ImageView imgPenyakit;
        CardView cardView;

        public PenyakitViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.itemPenyakit);
            namaPenyakit = itemView.findViewById(R.id.txtNamaPenyakitItem);
            imgPenyakit = itemView.findViewById(R.id.imgPenyakitItem);

        }
    }
}
