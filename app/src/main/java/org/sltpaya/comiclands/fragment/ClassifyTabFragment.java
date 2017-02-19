package org.sltpaya.comiclands.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.ClassifyAdapter;
import org.sltpaya.comiclands.net.ClassifyHttp;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.entry.ClassifyEntry;
import org.sltpaya.tool.Toast;
import org.sltpaya.tool.Utils;
import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class ClassifyTabFragment extends Fragment {

    private View mRootView;
    private ClassifyAdapter adapter;
    private ClassifyEntry mEntry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_recycler, container, false);
        init();
        return mRootView;
    }

    private void init() {
        mRootView.setBackgroundColor(0xFFEEEEEE);
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        recyclerView.setPadding(Utils.dp2px(10), Utils.dp2px(10), 0, 0);//设置RecyclerView左方padding
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new ClassifyAdapter(getContext());
        recyclerView.setAdapter(adapter);
        if (mEntry == null) {
            ClassifyHttp.requestClassifyJson();/*请求网络数据--分类*/
            processNetEvent();
            return;
        }
        adapter.notifyDataChanged(mEntry);
    }

    private void processNetEvent() {
        NetObserver<ClassifyEntry> observer = ClassifyHttp.getClassifyObserver();
        observer.registerNetObserver(new NetListener<ClassifyEntry>() {
            @Override
            public void onResponseSuccessed(ClassifyEntry entry) {
                if (entry.getCode() == 200) {
                    mEntry = entry;
                    adapter.notifyDataChanged(entry);
                }
            }

            @Override
            public void onFailed(Call<ClassifyEntry> call, Throwable t) {
                Toast.makeText(getContext(), "分类获取网络数据失败了...", Toast.LENGTH_LONG).show();
            }
        });
    }

}

