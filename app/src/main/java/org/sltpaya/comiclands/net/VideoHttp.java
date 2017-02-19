package org.sltpaya.comiclands.net;

import android.util.Log;
import org.sltpaya.comiclands.net.entry.VideoEntry;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
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
public class VideoHttp {

    private static final String TAG = "VideoHttp";
    private static final boolean DEBUG = true;

    private static NetObserver<VideoEntry> videoObserver = new NetObserver<>();
    private VideoHttp(){}

    public static void requestVideoJson() {
        VideoService recommendService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(VideoService.class);

        Map<String, String> customMap = getDefaultFiled();

        Call<VideoEntry> request = recommendService.request(customMap);
        request.enqueue(new Callback<VideoEntry>() {
            @Override
            public void onResponse(Call<VideoEntry> call, Response<VideoEntry> response) {
                if (response == null) {
                    logger("结果为空，网络错误.");
                    return;
                }
                videoObserver.onResponseSuccessed(response.body());
            }

            @Override
            public void onFailure(Call<VideoEntry> call, Throwable t) {
                logger("网络连接失败了！");
                videoObserver.onFailed(call, t);
            }
        });
    }

    public static NetObserver<VideoEntry> getVideoObserver() {
        return videoObserver;
    }

    private static Map<String, String> getDefaultFiled() {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("appversion", "3.9.30");
        typeMap.put("appVersionName", "3.9.30");
        typeMap.put("channel", "myapp");
        typeMap.put("channelId", "myapp");
        typeMap.put("apptype", "6");
        typeMap.put("appType", "6");

        typeMap.put("e", "1486713090");
        typeMap.put("token", "3d4ef1841977868dbc46a588d560eeef");
        typeMap.put("maxtargetmethod", "99");
        typeMap.put("platformtype", "1");
        typeMap.put("adgroupid", "112");
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

    interface VideoService {

        @GET("comic_v2/vediohome")
        Call<VideoEntry> request(@QueryMap Map<String, String> type);

    }


}
