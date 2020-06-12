package com.tobatani.del.api;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    @Headers({
            "Prediction-Key: 42b03f551d0a439785a7f601b92a18fb",
            "Content-Type: application/octet-stream"
    })
    @Multipart
    @POST("/customvision/v3.0/Prediction/8e1ce2b6-26e1-4866-b622-ad8b4717b6c3/classify/iterations/Iteration6/image/")
    Call<ResponseBody> uploadImage(
            @Part MultipartBody.Part file
    );
}
