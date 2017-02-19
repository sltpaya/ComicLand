package org.sltpaya.comiclands.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.ClassifyHolder;
import org.sltpaya.comiclands.net.entry.ClassifyEntry;

import java.util.List;

import static android.R.string.no;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class ClassifyAdapter extends TabAdapter<ClassifyEntry> {

    private final LayoutInflater mInflater;
    private List<ClassifyEntry.Adlistjson> adlistjson;

    public ClassifyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void notifyDataChanged(ClassifyEntry entry) {
        if (entry.getCode() == 200) {
            adlistjson = entry.getInfo().getAdlistjson();
            notifyDataSetChanged();
        }
    }

    @Override
    public ClassifyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_classify, parent, false);
        return new ClassifyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ClassifyHolder)holder).setData(adlistjson.get(position));
    }

    @Override
    public int getItemCount() {
        return adlistjson == null ? 0 : adlistjson.size();
    }
}
