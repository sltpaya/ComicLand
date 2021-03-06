package org.sltpaya.comiclands.net;

import android.util.Log;
import org.sltpaya.comiclands.net.entry.RankListEntry;
import org.sltpaya.comiclands.observer.NetObserver;

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
 * Date: 2017/2/17
 */
public class RankListHttp {

    private static final String TAG = "RankListHttp";
    private static final boolean DEBUG = true;

    private static NetObserver<RankListEntry> rankListObserver = new NetObserver<>();

    private RankListHttp(){}

    public static void requestRankListJson() {
        RankListService recommendService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(RankListService.class);

        Map<String, String> customMap = getDefaultFiled();

        Call<RankListEntry> request = recommendService.request(customMap);
        request.enqueue(new Callback<RankListEntry>() {
            @Override
            public void onResponse(Call<RankListEntry> call, Response<RankListEntry> response) {
                if (response == null) {
                    logger("结果为空，网络错误.");
                    return;
                }
                rankListObserver.onResponseSuccessed(response.body());
            }

            @Override
            public void onFailure(Call<RankListEntry> call, Throwable t) {
                logger("网络连接失败了！");
                rankListObserver.onFailed(call, t);
            }
        });
    }

    public static NetObserver<RankListEntry> getRankListObserver() {
        return rankListObserver;
    }

    private static Map<String, String> getDefaultFiled() {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("appversion", "3.9.30");
        typeMap.put("appVersionName", "3.9.30");
        typeMap.put("channel", "myapp");
        typeMap.put("channelId", "myapp");
        typeMap.put("apptype", "6");
        typeMap.put("appType", "6");

        typeMap.put("e", "1487334135");
        typeMap.put("retype", "1");
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

    private interface RankListService {

        @GET("comic/recommendspecial_sb")
        Call<RankListEntry> request(@QueryMap Map<String, String> type);

    }

}
