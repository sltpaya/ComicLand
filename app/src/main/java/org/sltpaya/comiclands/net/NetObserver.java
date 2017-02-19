package org.sltpaya.comiclands.net;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/13
 */
public class NetObserver<N> implements NetListener<N> {

    private ArrayList<NetListener<N>> listeners = new ArrayList<>();

    @Override
    public void onResponseSuccessed(N entry) {
        for (NetListener<N> listener : listeners) {
            listener.onResponseSuccessed(entry);
        }
    }

    @Override
    public void onFailed(Call<N> call, Throwable t) {
        for (NetListener<N> listener : listeners) {
            listener.onFailed(call, t);
        }
    }

    public void registerNetObserver(NetListener<N> listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unRegisterNetObserver(NetListener<N> listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void clearRegisterNetObserver() {
        listeners.clear();
    }

}
