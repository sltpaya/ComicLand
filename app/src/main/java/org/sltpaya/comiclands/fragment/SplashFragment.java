package org.sltpaya.comiclands.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.activity.MainActivity;
import org.sltpaya.comiclands.observer.NetListener;
import org.sltpaya.comiclands.observer.NetObserver;
import org.sltpaya.comiclands.net.SplashHttp;
import org.sltpaya.comiclands.net.entry.SplashEntry;
import org.sltpaya.tool.Utils;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/10
 */
public class SplashFragment extends Fragment {

    private final int DELAY_TIME = 2 * 1000;

    private View mRootView;
    private ImageView mImg;
    private View mPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_splash, container, false);
        init();
        return mRootView;
    }

    private void init() {
        initViews();
        requestSplashImg();
        initEvent();
    }

    private void initViews() {
        mImg = (ImageView) mRootView.findViewById(R.id.splash_image);
        mPass = mRootView.findViewById(R.id.splash_pass);
    }

    private void requestSplashImg() {
        NetObserver<SplashEntry> observer = SplashHttp.getSplashObserver();
        observer.registerNetObserver(new NetListener<SplashEntry>() {
            @Override
            public void onResponseSuccessed(SplashEntry entry) {
                if (entry.getCode() == 200) {
                    String imgUrl = entry.getInfo().getAdlistjson().get(0).getImageurl();
                    //TODO:有异常出现：Context must not be null.
                    Picasso.with(Utils.getContext()).load(imgUrl).into(mImg);
                }
            }

            @Override
            public void onFailed(Call<SplashEntry> call, Throwable t) {
            }
        });
    }

    private void initEvent() {
        final Timer timer = new Timer("Splash_Timer");
        mPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                enterMain();
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                enterMain();
            }
        }, DELAY_TIME);
    }

    private void enterMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

}
