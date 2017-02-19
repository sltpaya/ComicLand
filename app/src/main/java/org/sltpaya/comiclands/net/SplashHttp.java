package org.sltpaya.comiclands.net;

import android.util.Log;
import com.google.gson.Gson;
import org.sltpaya.comiclands.net.entry.SplashEntry;
import org.sltpaya.comiclands.observer.NetObserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import static org.sltpaya.comiclands.consts.Consts.BASE_URL;

/**
 * Author: SLTPAYA
 * Date: 2017/2/15
 */
public class SplashHttp {

    private static final String TAG = "SplashHttp";
    private static final boolean DEBUG = true;

    private static NetObserver<SplashEntry> splashObserver = new NetObserver<>();
    private SplashHttp() {}

    public static void requestSplashJson() {
        SplashHttp.SplashService splashService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(SplashHttp.SplashService.class);

        Map<String, String> customMap = getDefaultFiled();

        final Call<ResponseBody> request = splashService.request(customMap);

        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response == null) {
                    logger("网络错误.");
                    return;
                }
                SplashEntry splashEntry = parseJson(response);
                splashObserver.onResponseSuccessed(splashEntry);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                logger("网络连接失败了！");
            }
        });
    }

    private static SplashEntry parseJson(Response<ResponseBody> response) {
        String result = null;
        try {
            result = response.body().string()
                    .replace("\\", "")
                    .replace("\"[", "[")
                    .replace("]\"", "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(result, SplashEntry.class);
    }

    public static NetObserver<SplashEntry> getSplashObserver() {
        return splashObserver;
    }

    private static Map<String, String> getDefaultFiled() {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("appversion", "3.9.30");
        typeMap.put("appVersionName", "3.9.30");
        typeMap.put("channel", "myapp");
        typeMap.put("channelId", "myapp");
        typeMap.put("apptype", "6");
        typeMap.put("appType", "6");
        typeMap.put("e", "1487145493");
        typeMap.put("platformtype", "1");
        typeMap.put("maxtargetmethod", "99");
        typeMap.put("token", "7c9f13ab12e8ce9d52f9992819b9e80d");
        typeMap.put("adgroupid", "45");
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
                Log.d("zcb", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    private interface SplashService {

        @POST("comic/getproad")
        Call<ResponseBody> request(@QueryMap Map<String, String> type);

    }

}
