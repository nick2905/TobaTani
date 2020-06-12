package com.tobatani.del.ui.penyakit;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tobatani.del.R;
import com.tobatani.del.model.PenyakitItem;

import java.util.ArrayList;

public class ListPenyakit extends AppCompatActivity {
    RecyclerView rvPenyakit;
    private final ArrayList<PenyakitItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_penyakit);
        getSupportActionBar().setTitle("Penyakit Tanaman");
        rvPenyakit = findViewById(R.id.rvPenyakit);
        rvPenyakit.setHasFixedSize(true);

        if (savedInstanceState == null) {
            list.addAll(getListPenyakit());
            showRecyclerGrid();
        }
    }

    private ArrayList<PenyakitItem> getListPenyakit() {
        String[] dataName = getResources().getStringArray(R.array.dataNamaPenyakit);
        String[] dataLatin = getResources().getStringArray(R.array.dataNamaLatinPenyakit);
        String[] dataDeskripsi = getResources().getStringArray(R.array.dataDeskripsiPenyakit);
        String[] dataCiri = getResources().getStringArray(R.array.dataCiriCiriPenyakit);
        String[] dataSolusi = getResources().getStringArray(R.array.dataSolusiTanaman);
        String[] dataSerang = getResources().getStringArray(R.array.dataTanamanYangDiserang);
        TypedArray dataImage = getResources().obtainTypedArray(R.array.dataImagePenyakit);

        ArrayList<PenyakitItem> listPenyakit = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            PenyakitItem penyakitItem = new PenyakitItem();
            penyakitItem.setNamaPenyakit(dataName[i]);
            penyakitItem.setNamaLatin(dataLatin[i]);
            penyakitItem.setDeskripsiPenyakit(dataDeskripsi[i]);
            penyakitItem.setCiriPenyakit(dataCiri[i]);
            penyakitItem.setSolusiPenyakit(dataSolusi[i]);
            penyakitItem.setSerangTanamanPenyakit(dataSerang[i]);
            penyakitItem.setImgPenyakit(dataImage.getResourceId(i, -1));
            listPenyakit.add(penyakitItem);
        }
        return listPenyakit;
    }

    private void showRecyclerGrid() {
        rvPenyakit.setLayoutManager(new GridLayoutManager(this, 2));
        PenyakitAdapter penyakitAdapter = new PenyakitAdapter(list, this);
        rvPenyakit.setAdapter(penyakitAdapter);
    }
}
