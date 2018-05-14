package com.mirkowu.fastread.network;

import android.support.annotation.Nullable;

import io.reactivex.Observer;

/**
 * 网络请求流程接口
 */

public interface Callback<T> extends Observer<T> {
    /*** 请求网络开始前，UI线程 */
    void onStart();

    /*** 对返回数据进行操作的回调， UI线程 */
    void onSuccess(@Nullable T data);


    /*** 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程 */
    void onError(Throwable throwable);

    /*** 请求网络结束后，UI线程 */
    void onFinish();
}
