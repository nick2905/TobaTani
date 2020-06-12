package com.tobatani.del.ui.detail.penyakit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.tobatani.del.R;
import com.tobatani.del.ui.main.MainActivity;

public class DetailPenyakitActivity extends AppCompatActivity {
    TextView txtNama, txtLatin, txtDeskripsi, txtCiri, txtSolusi, txtTanaman;
    ImageView imgPenyakit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);
        Toolbar toolbar = findViewById(R.id.toolbarDetailHama);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        context = getApplicationContext();
        Intent intent = getIntent();
        String title = intent.getExtras().getString("TITLE");
        String latin = intent.getExtras().getString("LATIN");
        String deskripsi = intent.getExtras().getString("DESKRIPSI");
        String ciri = intent.getExtras().getString("CIRI");
        String solusi = intent.getExtras().getString("SOLUSI");
        String tanaman = intent.getExtras().getString("TANAMAN");
        int image = intent.getExtras().getInt("IMAGE");

        txtNama = findViewById(R.id.txtNamaPenyakit);
        txtLatin = findViewById(R.id.txtNamaLatinPenyakit);
        txtDeskripsi = findViewById(R.id.txtDeskripsiPenyakit);
        txtCiri = findViewById(R.id.txtCiriPenyakit);
        txtSolusi = findViewById(R.id.txtSolusiPenyakit);
        txtTanaman = findViewById(R.id.txtJenisTanamanPenyakit);
        imgPenyakit = findViewById(R.id.imgPenyakit);

        txtNama.setText(title);
        txtLatin.setText(latin);
        txtDeskripsi.setText(deskripsi);
        txtCiri.setText(ciri);
        txtSolusi.setText(solusi);
        txtTanaman.setText(tanaman);
        Glide.with(context)
                .load(image)
                .into(imgPenyakit);
    }
}

