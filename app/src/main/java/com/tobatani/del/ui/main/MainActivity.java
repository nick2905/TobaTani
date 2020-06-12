package com.tobatani.del.ui.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tobatani.del.R;
import com.tobatani.del.model.TanahItem;
import com.tobatani.del.ui.camerapenyakit.CameraPenyakitActivity;
import com.tobatani.del.ui.cameratanah.CameraTanahActivity;
import com.tobatani.del.ui.penyakit.ListPenyakit;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CardView cardview, cvPenyakit;
    RecyclerView rvTanah;
    TextView txtLihatSemua;
    private final ArrayList<TanahItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTanah = findViewById(R.id.recyclerView);
        rvTanah.setHasFixedSize(true);

        if (savedInstanceState == null) {
            list.addAll(getListTanah());
            showRecyclerHorizontal();
        }

        cardview = findViewById(R.id.deteksiTanah);
        cvPenyakit = findViewById(R.id.deteksiPenyakitTanaman);

        txtLihatSemua = findViewById(R.id.txtLihatSemua);
        cardview.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), CameraTanahActivity.class)));
        cvPenyakit.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), CameraPenyakitActivity.class)));
        txtLihatSemua.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ListPenyakit.class)));
    }

    private ArrayList<TanahItem> getListTanah() {
        String[] dataName = getResources().getStringArray(R.array.dataNamaTanah);
        String[] dataDeskripsi = getResources().getStringArray(R.array.dataDeskripsiTanah);
        String[] dataTanaman = getResources().getStringArray(R.array.dataTanaman);
        String[] dataWilayah = getResources().getStringArray(R.array.dataWilayah);

        TypedArray dataImage = getResources().obtainTypedArray(R.array.dataGambar);

        ArrayList<TanahItem> listTanah = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            TanahItem tanahItem = new TanahItem();
            tanahItem.setNameTanah(dataName[i]);
            tanahItem.setDeskripsiTanah(dataDeskripsi[i]);
            tanahItem.setDataTanaman(dataTanaman[i]);
            tanahItem.setDataWilayah(dataWilayah[i]);
            tanahItem.setImgTanah(dataImage.getResourceId(i, -1));
            listTanah.add(tanahItem);
        }
        return listTanah;
    }

    private void showRecyclerHorizontal() {
        rvTanah.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        TanahAdapter tanahAdapter = new TanahAdapter(list, this);
        rvTanah.setAdapter(tanahAdapter);
    }
}
