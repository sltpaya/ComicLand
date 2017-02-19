package org.sltpaya.comiclands.observer;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/13
 */
public interface NetListener<N> {

    void onResponseSuccessed(N entry);
    void onFailed(Call<N> call, Throwable t);

}
