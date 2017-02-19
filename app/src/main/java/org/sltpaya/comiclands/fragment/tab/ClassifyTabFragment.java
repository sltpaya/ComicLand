package org.sltpaya.comiclands.fragment.tab;

import android.support.v7.widget.GridLayoutManager;

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
public class ClassifyTabFragment extends BaseTabFragment<ClassifyEntry> {

    @Override
    protected void setRecyclerAttribute() {
        mRootView.setBackgroundColor(0xFFEEEEEE);
        mRecyclerView.setPadding(Utils.dp2px(10), Utils.dp2px(10), 0, 0);//设置RecyclerView左方padding
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new ClassifyAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void requestNetWork() {
        ClassifyHttp.requestClassifyJson();/*请求网络数据--分类*/
    }

    @Override
    protected void processNetEvent() {
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

