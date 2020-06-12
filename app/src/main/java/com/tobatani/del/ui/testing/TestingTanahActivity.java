package com.tobatani.del.ui.testing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tobatani.del.R;
import com.tobatani.del.ui.detail.analisa.DetailAnalisaTanah;

public class TestingTanahActivity extends AppCompatActivity {
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_tanah);

        data = "basah";
        getDataTanahResources(data);
    }

    @SuppressLint("ResourceType")
    public void getDataTanahResources(String data) {
        String[] dataTitle = getResources().getStringArray(R.array.dataJudulAnalisaTanah);
        String[] dataDeskripsi = getResources().getStringArray(R.array.dataDeskripsiAnalisaTanah);
        TypedArray dataImage = getResources().obtainTypedArray(R.array.dataImageAnalisaTanah);

        String title, deskripsi;
        int image;

        if (data == "basah") {
            title = dataTitle[0];
            deskripsi = dataDeskripsi[0];
            image = dataImage.getResourceId(0, -1);

            Intent intent = new Intent(this, DetailAnalisaTanah.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESCRIPTION", deskripsi);
            intent.putExtra("IMAGE", image);
            startActivity(intent);
            finish();

        } else if (data == "cukupkering") {
            title = dataTitle[1];
            deskripsi = dataDeskripsi[1];
            image = dataImage.getResourceId(1, -1);

            Intent intent = new Intent(this, DetailAnalisaTanah.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESCRIPTION", deskripsi);
            intent.putExtra("IMAGE", image);
            startActivity(intent);
            finish();

        } else if (data == "kering") {
            title = dataTitle[2];
            deskripsi = dataDeskripsi[2];
            image = dataImage.getResourceId(2, -1);

            Intent intent = new Intent(this, DetailAnalisaTanah.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESCRIPTION", deskripsi);
            intent.putExtra("IMAGE", image);
            startActivity(intent);
            finish();

        } else if (data == "keringparah") {
            title = dataTitle[3];
            deskripsi = dataDeskripsi[3];
            image = dataImage.getResourceId(3, -3);

            Intent intent = new Intent(this, DetailAnalisaTanah.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESCRIPTION", deskripsi);
            intent.putExtra("IMAGE", image);
            startActivity(intent);
            finish();

        }
    }
}
