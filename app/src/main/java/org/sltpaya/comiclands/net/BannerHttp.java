package org.sltpaya.comiclands.net;

import android.util.Log;

import com.google.gson.Gson;

import org.sltpaya.comiclands.net.entry.BannerEntry;
import org.sltpaya.comiclands.observer.BannerObserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import static org.sltpaya.comiclands.consts.Consts.BASE_URL;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class BannerHttp {

    private final String TAG = "BannerHttp";
    private final boolean DEBUG = true;
    private int mGroupId;

    private static BannerObserver bannerObserver = new BannerObserver();

    public BannerHttp(int mGroupId) {
        this.mGroupId = mGroupId;
    }

    public void requestBannerJson() {
        RecommendBannerService recommendService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
                .create(RecommendBannerService.class);

        Map<String, String> customMap = getDefaultFiled();
        customMap.put("adgroupid", String.valueOf(mGroupId));

        Call<ResponseBody> request = recommendService.request(customMap);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response == null) {
                    logger("网络错误.");
                    return;
                }
                BannerEntry bannerEntry = parseJson(response);
                System.out.println("正在回调！"+mGroupId);
                bannerObserver.onResponseSuccessed(bannerEntry, mGroupId);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                logger("网络连接失败了！");
            }
        });
    }

    private BannerEntry parseJson(Response<ResponseBody> response) {
        String result = null;
        try {
            result = response.body().string()
                    .replace("\\", "")
                    .replace("\"[", "[")
                    .replace("]\"", "]");
            logger("请求结果为："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(result, BannerEntry.class);
    }

    public static BannerObserver getBannerObserver() {
        return bannerObserver;
    }

    private Map<String, String> getDefaultFiled() {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("appversion", "3.9.30");
        typeMap.put("appVersionName", "3.9.30");
        typeMap.put("channel", "myapp");
        typeMap.put("channelId", "myapp");
        typeMap.put("apptype", "6");
        typeMap.put("appType", "6");
        typeMap.put("maxtargetmethod", "99");
        typeMap.put("platformtype", "1");
        return typeMap;
    }

    private void logger(String des) {
        if (DEBUG) {
            Log.d(TAG, des);
        }
    }

    private interface RecommendBannerService {

        @GET("comic/getproad")
        Call<ResponseBody> request(@QueryMap Map<String, String> type);

    }

}
