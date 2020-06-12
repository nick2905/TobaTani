package com.tobatani.del.ui.detail.analisa;

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

public class DetailAnalisaTanah extends AppCompatActivity {
    TextView txtTitle, txtDeskripsi;
    ImageView imgTanah;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_analisa_tanah);

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
        int image = intent.getExtras().getInt("IMAGE");

        txtTitle = findViewById(R.id.txtNamaTanahAnalisa);
        txtDeskripsi = findViewById(R.id.txtDeskripsiTanahAnalisa);
        imgTanah = findViewById(R.id.imgTanahAnalisa);

        txtTitle.setText(title);
        txtDeskripsi.setText(description);
        Glide.with(context)
                .load(image)
                .into(imgTanah);
    }
}
