package com.tobatani.del.utils;

import com.tobatani.del.api.APIService;
import com.tobatani.del.api.RetrofitClient;

public class APIUtils {
    //Localhost  = 127.0.0.1 atau 10.0.0.1
    public static final String BASE_URL_API = "https://southeastasia.api.cognitive.microsoft.com/customvision/v3.0/Prediction/8e1ce2b6-26e1-4866-b622-ad8b4717b6c3/classify/iterations/Iteration6/image/";
    public static final String BASE_URL_HAMA = "https://southeastasia.api.cognitive.microsoft.com/customvision/v3.0/Prediction/6eaa00c8-9d41-4f7c-8909-7bbcad922907/classify/iterations/iteration/image";

    //Deklarasi Interface ApiService
    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL_API).create(APIService.class);
    }
}