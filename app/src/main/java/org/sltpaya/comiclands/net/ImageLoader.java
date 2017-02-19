package org.sltpaya.comiclands.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import org.sltpaya.tool.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Author: SLTPAYA
 * Date: 2017/2/15
 */
public class ImageLoader {

    private static final String localPath = Environment
            .getExternalStorageDirectory()
            .getAbsolutePath()
            + "/Android/data/"
            + Utils.getContext().getPackageName()
            + "/imgs";
    private static LruCache<String, Bitmap> bitmapLruCache = new LruCache<>(20);

    private static String base64ImgUrl;
    private static String rawImgUrl;
    private static Bitmap bitmap;

    public static void loadImg(String imgUrl, ImageView imageView) {

//        base64ImgUrl = Base64.encodeToString(imgUrl.getBytes(), Base64.DEFAULT);
        rawImgUrl = imgUrl;
        base64ImgUrl = rawImgUrl.replace("/", "").replace(".", "");

        dirIsExist();
        bitmap = bitmapLruCache.get(base64ImgUrl);
        if (bitmap != null) {
            logger("从内存中加载到了图片！" + rawImgUrl);
            imageView.setImageBitmap(bitmap);
            return;
        }
        bitmap = findFileToDisk();
        if (bitmap != null) {
            logger("从本地磁盘中加载到了图片！" + rawImgUrl + " 目标：" + base64ImgUrl);
            imageView.setImageBitmap(bitmap);
            return;
        }

        requestNetResource(imageView);
    }


    /**
     * 从本地磁盘中查找文件
     *
     * @return
     */
    private static Bitmap findFileToDisk() {
        File imgFile = new File(localPath, base64ImgUrl);
        if (imgFile.exists()) {
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(imgFile));
                bitmapLruCache.put(base64ImgUrl, bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private static void dirIsExist() {
        File file = new File(localPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                logger("文件夹已经被创建了！");
            } else {
                logger("文件存在，但不是文件夹");
            }
        } else {
            boolean mkdir = file.mkdirs();
            if (mkdir) {
                logger("文件夹创建成功！");
            } else {
                logger("文件夹创建失败！");
            }
        }
    }

    /**
     * 请求网络上的资源
     *
     * @return Bitmap
     */
    private static void requestNetResource(final ImageView imageView) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://m.baidu.com/").build();
        NetImage netImage = retrofit.create(NetImage.class);
        Call<ResponseBody> call = netImage.downloadImgFile(rawImgUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Thread() {
                        @Override
                        public void run() {
                            final InputStream stream = response.body().byteStream();
                            writeImageToDisk(stream);
                            logger("从网络中加载到了图片而且写入到了本地.路径为：" + base64ImgUrl);
                            bitmap = findFileToDisk();
                            if (bitmap != null) {
                                Utils.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        logger("从流中成功加载到了图片！");
//                                        Bitmap bitmap = BitmapFactory.decodeStream(stream);
                                        imageView.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        }
                    }.start();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    /**
     * 将文件写入磁盘中
     *
     * @return boolean是否写入完成
     */
    private static void writeImageToDisk(InputStream inputStream) {
        File imgFile = new File(localPath, base64ImgUrl);
        OutputStream os = null;
        try {
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            os = new FileOutputStream(imgFile);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void logger(String des) {
        Log.d("ImageLoader", des);
    }

    private interface NetImage {

        @Streaming
        @GET
        Call<ResponseBody> downloadImgFile(@Url String url);

    }
}
