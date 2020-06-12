package com.tobatani.del.ui.testing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tobatani.del.R;
import com.tobatani.del.ui.detail.penyakit.DetailPenyakitActivity;

public class TestingActivity extends AppCompatActivity {
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        data = "potassiumdeficiency";
        getDataHamaResources(data);
    }

    @SuppressLint("ResourceType")
    public void getDataHamaResources(String data) {
        String[] dataTitle = getResources().getStringArray(R.array.dataNamaPenyakit);
        String[] dataLatin = getResources().getStringArray(R.array.dataNamaLatinPenyakit);
        String[] dataDeskripsi = getResources().getStringArray(R.array.dataDeskripsiPenyakit);
        String[] dataCiri = getResources().getStringArray(R.array.dataCiriCiriPenyakit);
        String[] dataSolusi = getResources().getStringArray(R.array.dataSolusiTanaman);
        String[] dataTanaman = getResources().getStringArray(R.array.dataTanamanYangDiserang);
        TypedArray dataImage = getResources().obtainTypedArray(R.array.dataImagePenyakit);

        String title, latin, deskripsi, ciri, solusi, tanaman;
        int gambar;

        if (data == "aphids") {
//            Toast.makeText(this, nama, Toast.LENGTH_SHORT).show();
            title = dataTitle[0];
            latin = dataLatin[0];
            deskripsi = dataDeskripsi[0];
            ciri = dataCiri[0];
            solusi = dataSolusi[0];
            tanaman = dataTanaman[0];
            gambar = dataImage.getResourceId(0, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "downymildew") {
            title = dataTitle[1];
            latin = dataLatin[1];
            deskripsi = dataDeskripsi[1];
            ciri = dataCiri[1];
            solusi = dataSolusi[1];
            tanaman = dataTanaman[1];
            gambar = dataImage.getResourceId(1, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "layufusarium") {
            title = dataTitle[2];
            latin = dataLatin[2];
            deskripsi = dataDeskripsi[2];
            ciri = dataCiri[2];
            solusi = dataSolusi[2];
            tanaman = dataTanaman[2];
            gambar = dataImage.getResourceId(2, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "magnesiumdeficiency") {
            title = dataTitle[3];
            latin = dataLatin[3];
            deskripsi = dataDeskripsi[3];
            ciri = dataCiri[3];
            solusi = dataSolusi[3];
            tanaman = dataTanaman[3];
            gambar = dataImage.getResourceId(3, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();


        } else if (data == "mealybug") {
            title = dataTitle[4];
            latin = dataLatin[4];
            deskripsi = dataDeskripsi[4];
            ciri = dataCiri[4];
            solusi = dataSolusi[4];
            tanaman = dataTanaman[4];
            gambar = dataImage.getResourceId(4, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "nitrogendeficiency") {
            title = dataTitle[5];
            latin = dataLatin[5];
            deskripsi = dataDeskripsi[5];
            ciri = dataCiri[5];
            solusi = dataSolusi[5];
            tanaman = dataTanaman[5];
            gambar = dataImage.getResourceId(5, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "potassiumdeficiency") {
            title = dataTitle[6];
            latin = dataLatin[6];
            deskripsi = dataDeskripsi[6];
            ciri = dataCiri[6];
            solusi = dataSolusi[6];
            tanaman = dataTanaman[6];
            gambar = dataImage.getResourceId(6, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "powerymildew") {
            title = dataTitle[7];
            latin = dataLatin[7];
            deskripsi = dataDeskripsi[7];
            ciri = dataCiri[7];
            solusi = dataSolusi[7];
            tanaman = dataTanaman[7];
            gambar = dataImage.getResourceId(7, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "spidermites") {
            title = dataTitle[8];
            latin = dataLatin[8];
            deskripsi = dataDeskripsi[8];
            ciri = dataCiri[8];
            solusi = dataSolusi[8];
            tanaman = dataTanaman[8];
            gambar = dataImage.getResourceId(8, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "thrips") {
            title = dataTitle[9];
            latin = dataLatin[9];
            deskripsi = dataDeskripsi[9];
            ciri = dataCiri[9];
            solusi = dataSolusi[9];
            tanaman = dataTanaman[9];
            gambar = dataImage.getResourceId(9, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();

        } else if (data == "tungro") {
            title = dataTitle[10];
            latin = dataLatin[10];
            deskripsi = dataDeskripsi[10];
            ciri = dataCiri[10];
            solusi = dataSolusi[10];
            tanaman = dataTanaman[10];
            gambar = dataImage.getResourceId(10, -1);

            Intent intent = new Intent(this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            startActivity(intent);
            finish();
        }
    }
}
