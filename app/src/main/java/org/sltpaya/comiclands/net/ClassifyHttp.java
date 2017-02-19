package org.sltpaya.comiclands.net;

import android.util.Log;

import com.google.gson.Gson;

import org.sltpaya.comiclands.net.entry.ClassifyEntry;
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
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static org.sltpaya.comiclands.consts.Consts.BASE_URL;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class ClassifyHttp {

    private static final String TAG = "ClassifyHttp";
    private static final boolean DEBUG = true;

    private static NetObserver<ClassifyEntry> classifyObserver = new NetObserver<>();

    private ClassifyHttp() {
    }

    public static void requestClassifyJson() {
        ClassifyService recommendService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(ClassifyService.class);

        Map<String, String> customMap = getDefaultFiled();

        Call<ResponseBody> request = recommendService.request(customMap);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response == null) {
                    logger("结果为空，网络错误.");
                    return;
                }
                ClassifyEntry entry = parseJson(response);
                classifyObserver.onResponseSuccessed(entry);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                logger("网络连接失败了！");
            }
        });
    }

    private static ClassifyEntry parseJson(Response<ResponseBody> response) {
        String result = null;
        try {
            result = response.body().string()
                    .replace("\\", "")
                    .replace("\"[", "[")
                    .replace("]\"", "]");
            logger("获取到的内容："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(result, ClassifyEntry.class);
    }

    public static NetObserver<ClassifyEntry> getClassifyObserver() {
        return classifyObserver;
    }

    private static Map<String, String> getDefaultFiled() {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("appversion", "3.9.30");
        typeMap.put("appVersionName", "3.9.30");
        typeMap.put("channel", "myapp");
        typeMap.put("channelId", "myapp");
        typeMap.put("apptype", "6");
        typeMap.put("appType", "6");
        typeMap.put("maxtargetmethod", "99");
        typeMap.put("platformtype", "1");

        typeMap.put("e", "1487382024");
        typeMap.put("adgroupid", "131");
        return typeMap;
    }

    private static void logger(String des) {
        if (DEBUG) {
            Log.d(TAG, des);
        }
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor
                = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    private interface ClassifyService {

        @GET("comic/getproad")
        Call<ResponseBody> request(@QueryMap Map<String, String> type);

    }


}
