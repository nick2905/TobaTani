package com.tobatani.del.ui.detail.tanah;

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

public class DetailTanahActivity extends AppCompatActivity {
    TextView txtTitle, txtDeskripsi, txtJenisTanaman, txtWilayah;
    ImageView imgTanah;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanah);

        Toolbar toolbar = findViewById(R.id.toolbarDetailTanah);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        context = getApplicationContext();
        Intent intent = getIntent();
        String title = intent.getExtras().getString("TITLE");
        String description = intent.getExtras().getString("DESCRIPTION");
        String tanaman = intent.getExtras().getString("TANAMAN");
        String wilayah = intent.getExtras().getString("WILAYAH");
        int image = intent.getExtras().getInt("IMAGE");

        txtTitle = findViewById(R.id.txtNamaTanah);
        txtDeskripsi = findViewById(R.id.txtDeskripsiTanah);
        txtJenisTanaman = findViewById(R.id.txtJenisTanah);
        txtWilayah = findViewById(R.id.txtWilayahTanah);
        imgTanah = findViewById(R.id.imgTanah);

        txtTitle.setText(title);
        txtDeskripsi.setText(description);
        txtJenisTanaman.setText(tanaman);
        txtWilayah.setText(wilayah);
        Glide.with(context)
                .load(image)
                .into(imgTanah);
    }
}
