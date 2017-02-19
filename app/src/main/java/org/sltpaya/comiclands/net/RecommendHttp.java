package org.sltpaya.comiclands.net;

import android.util.Log;

import org.sltpaya.comiclands.net.entry.RecommendEntry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static org.sltpaya.comiclands.consts.Consts.BASE_URL;

/**
 * Author: SLTPAYA
 * Date: 2017/2/13
 */
public class RecommendHttp {

    private static final String TAG = "RecommedHttp";
    private static final boolean DEBUG = true;

    private static NetObserver<RecommendEntry> recommendObserver = new NetObserver<>();
    private RecommendHttp(){}

    public static void requestRecommendJson() {
        RecommendService recommendService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(RecommendService.class);

        Map<String, String> customMap = getDefaultFiled();

        Call<RecommendEntry> request = recommendService.request(customMap);
        request.enqueue(new Callback<RecommendEntry>() {
            @Override
            public void onResponse(Call<RecommendEntry> call, Response<RecommendEntry> response) {
                if (response == null) {
                    logger("结果为空，网络错误.");
                    return;
                }
                recommendObserver.onResponseSuccessed(response.body());
            }

            @Override
            public void onFailure(Call<RecommendEntry> call, Throwable t) {
                logger("网络连接失败了！");
                recommendObserver.onFailed(call, t);
            }
        });
    }

    public static NetObserver<RecommendEntry> getRecommendObserver() {
        return recommendObserver;
    }

    private static Map<String, String> getDefaultFiled() {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("appversion", "3.9.30");
        typeMap.put("channel", "myapp");
        typeMap.put("channelId", "myapp");
        typeMap.put("apptype", "6");
        typeMap.put("appType", "6");

        typeMap.put("e", "1486713090");
        typeMap.put("token", "3d4ef1841977868dbc46a588d560eeef");
        typeMap.put("viewtype", "1");
        return typeMap;
    }

    private static void logger(String des) {
        if (DEBUG) {
            Log.d(TAG, des);
        }
    }

    private static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor
                = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    interface RecommendService {

        @GET("comic_v2/customerview")
        Call<RecommendEntry> request(@QueryMap Map<String, String> type);

    }


}
