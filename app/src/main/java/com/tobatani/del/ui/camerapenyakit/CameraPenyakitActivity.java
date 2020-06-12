package com.tobatani.del.ui.camerapenyakit;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.tobatani.del.R;
import com.tobatani.del.api.APIService;
import com.tobatani.del.ui.detail.penyakit.DetailPenyakitActivity;
import com.tobatani.del.utils.APIUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class CameraPenyakitActivity extends AppCompatActivity {
    private Button btn_upload;
    private ImageView img_camera;
    private APIService service;
    private String as, picturePath, realPath, pictureName;
    private String probability, tagName;
    private ProgressDialog loading;
    private Bitmap image;
    private static String BASE_URL = APIUtils.BASE_URL_HAMA;

    public static final int PICK_IMAGE_CAMERA = 1;
    public static final int PICK_IMAGE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_tanah);

        service = APIUtils.getApiService();

        btn_upload = findViewById(R.id.btn_upload_image);
        img_camera = findViewById(R.id.img_get_image);

        img_camera.setOnClickListener(view -> {
            as = "takeImage";
            showAlertDialog(as);
        });

        btn_upload.setOnClickListener(view -> {
            as = "sendImage";
            showAlertDialog(as);
//            showToast(tagName);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_CAMERA) {
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(picturePath));
                img_camera.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_GALLERY) {
            try {
                Uri uri = data.getData();
                picturePath = getFileUri(uri);
                realPath = picturePath;

                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img_camera.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileUri(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);
        cursor.close();

        return path;
    }

    private void showAlertDialog(String type) {
        String dialogTitle, dialogMessage;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);

        if (type.equals("sendImage")) {
            dialogTitle = "Upload Image";
            dialogMessage = "Are you sure?";

            alertDialogBuilder.setTitle(dialogTitle);
            alertDialogBuilder.setMessage(dialogMessage);
            alertDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
                loading = ProgressDialog.show(this, null, "Harap Tunggu", true, false);
                if (!checkImage()) {
                    Toast.makeText(getApplicationContext(), "Image cannot be empty", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                } else {
                    if (checkPermission()) {
                        uploadImage();

                    } else {
                        requestPermission();
                    }
                }
            });
            alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
                dialog.cancel();
            });
        } else if (type.equals("takeImage")) {
            final CharSequence[] options = {
                    "Take Photo",
                    "Choose from Gallery",
                    "Cancel"
            };
            dialogTitle = "Choose one";

            alertDialogBuilder.setTitle(dialogTitle);
            alertDialogBuilder.setItems(options, (dialog, which) -> {
                if (options[which].equals("Take Photo")) {
                    try {
                        takePictureCamera();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (options[which].equals("Choose from Gallery")) {
                    takePictureGallery();
                } else if (options[which].equals("Cancel")) {
                    dialog.cancel();
                }
            });
        }

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    private void takePictureCamera() throws IOException {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photo = savePictureCamera();
            if (photo != null) {
                Uri uri = FileProvider.getUriForFile(
                        this,
                        "com.example.android.tobatani",
                        photo
                );

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, PICK_IMAGE_CAMERA);
            }
        }
    }

    private File savePictureCamera() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File picture = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        picturePath = "file://" + picture.getAbsolutePath();
        replaceFileString();
        return picture;
    }

    private void replaceFileString() {
        realPath = picturePath.replace("file://", "");
    }

    private void takePictureGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_GALLERY);
    }

    private boolean checkImage() {
        return img_camera.getDrawable() != null;
    }

    //OkHttp
    private void uploadImage() {
        File file = new File(realPath);

        pictureName = file.getName();
        OkHttpClient imageUploadClient = new OkHttpClient.Builder().addNetworkInterceptor(chain -> {
            Request originalRequest = chain.request();

            if (originalRequest.body() == null) {
                return chain.proceed(originalRequest);
            }
            Request progressRequest = originalRequest.newBuilder()
                    .build();

            return chain.proceed(progressRequest);

        }).build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", pictureName, RequestBody.create(file, MediaType.parse("image/*")))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .header("Prediction-Key", "42b03f551d0a439785a7f601b92a18fb")
                .header("Content-Type", "application/octet-stream")
                .post(requestBody)
                .build();

        imageUploadClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.e("debug", "onFailure: ERROR > " + e.getMessage());
                loading.dismiss();
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse: BERHASIL");
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        for (int i = 0; i < jsonObject.getJSONArray("predictions").length(); i++) {
                            probability = jsonObject.getJSONArray("predictions").getJSONObject(0).getString("probability");
                            tagName = jsonObject.getJSONArray("predictions").getJSONObject(0).getString("tagName");
                            Log.i("debug", "responseBody: " + tagName + " = " + probability);
                        }
                        Log.i("debug", "result: Berhasil");
                        loading.dismiss();
                        getDataHamaResources(tagName);
//                        runOnUiThread(() -> getDataTanahResources(tagName));
//                        runOnUiThread(() -> showToast(tagName));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("debug", "onResponse: GAGAL");
                    loading.dismiss();
                }
            }
        });
    }

    private boolean checkPermission() {
        return checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, PICK_IMAGE_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        switch (requestCode) {
            case PICK_IMAGE_GALLERY:
                if (grantResults.length > 0 && grantResults[0] <= PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    uploadImage();
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                    loading.dismiss();
                }
                break;
        }
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ResourceType")
    public void getDataHamaResources(String penyakit) {
        String[] dataTitle = getResources().getStringArray(R.array.dataNamaPenyakit);
        String[] dataLatin = getResources().getStringArray(R.array.dataNamaLatinPenyakit);
        String[] dataDeskripsi = getResources().getStringArray(R.array.dataDeskripsiPenyakit);
        String[] dataCiri = getResources().getStringArray(R.array.dataCiriCiriPenyakit);
        String[] dataSolusi = getResources().getStringArray(R.array.dataSolusiTanaman);
        String[] dataTanaman = getResources().getStringArray(R.array.dataTanamanYangDiserang);
        TypedArray dataImage = getResources().obtainTypedArray(R.array.dataImagePenyakit);

        String title, latin, deskripsi, ciri, solusi, tanaman;
        int gambar;

        if (penyakit.equals("aphids")) {
            title = dataTitle[0];
            latin = dataLatin[0];
            deskripsi = dataDeskripsi[0];
            ciri = dataCiri[0];
            solusi = dataSolusi[0];
            tanaman = dataTanaman[0];
            gambar = dataImage.getResourceId(0, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("downymildew")) {
            title = dataTitle[1];
            latin = dataLatin[1];
            deskripsi = dataDeskripsi[1];
            ciri = dataCiri[1];
            solusi = dataSolusi[1];
            tanaman = dataTanaman[1];
            gambar = dataImage.getResourceId(1, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("layufusarium")) {
            title = dataTitle[2];
            latin = dataLatin[2];
            deskripsi = dataDeskripsi[2];
            ciri = dataCiri[2];
            solusi = dataSolusi[2];
            tanaman = dataTanaman[2];
            gambar = dataImage.getResourceId(2, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("magnesiumdeficiency")) {
            title = dataTitle[3];
            latin = dataLatin[3];
            deskripsi = dataDeskripsi[3];
            ciri = dataCiri[3];
            solusi = dataSolusi[3];
            tanaman = dataTanaman[3];
            gambar = dataImage.getResourceId(3, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("mealybug")) {
            title = dataTitle[4];
            latin = dataLatin[4];
            deskripsi = dataDeskripsi[4];
            ciri = dataCiri[4];
            solusi = dataSolusi[4];
            tanaman = dataTanaman[4];
            gambar = dataImage.getResourceId(4, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("nitrogendeficiency")) {
            title = dataTitle[5];
            latin = dataLatin[5];
            deskripsi = dataDeskripsi[5];
            ciri = dataCiri[5];
            solusi = dataSolusi[5];
            tanaman = dataTanaman[5];
            gambar = dataImage.getResourceId(5, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("potassiumdeficiency")) {
            title = dataTitle[6];
            latin = dataLatin[6];
            deskripsi = dataDeskripsi[6];
            ciri = dataCiri[6];
            solusi = dataSolusi[6];
            tanaman = dataTanaman[6];
            gambar = dataImage.getResourceId(6, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("powerymildew")) {
            title = dataTitle[7];
            latin = dataLatin[7];
            deskripsi = dataDeskripsi[7];
            ciri = dataCiri[7];
            solusi = dataSolusi[7];
            tanaman = dataTanaman[7];
            gambar = dataImage.getResourceId(7, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("spidermites")) {
            title = dataTitle[8];
            latin = dataLatin[8];
            deskripsi = dataDeskripsi[8];
            ciri = dataCiri[8];
            solusi = dataSolusi[8];
            tanaman = dataTanaman[8];
            gambar = dataImage.getResourceId(8, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("thrips")) {
            title = dataTitle[9];
            latin = dataLatin[9];
            deskripsi = dataDeskripsi[9];
            ciri = dataCiri[9];
            solusi = dataSolusi[9];
            tanaman = dataTanaman[9];
            gambar = dataImage.getResourceId(9, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);

        } else if (penyakit.equals("tungro")) {
            title = dataTitle[10];
            latin = dataLatin[10];
            deskripsi = dataDeskripsi[10];
            ciri = dataCiri[10];
            solusi = dataSolusi[10];
            tanaman = dataTanaman[10];
            gambar = dataImage.getResourceId(10, -1);

            Intent intent = new Intent(CameraPenyakitActivity.this, DetailPenyakitActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("LATIN", latin);
            intent.putExtra("DESKRIPSI", deskripsi);
            intent.putExtra("CIRI", ciri);
            intent.putExtra("SOLUSI", solusi);
            intent.putExtra("TANAMAN", tanaman);
            intent.putExtra("IMAGE", gambar);
            CameraPenyakitActivity.this.startActivity(intent);
        }
    }
}

