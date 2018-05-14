package com.mirkowu.fastread.network;


import com.mirkowu.fastread.utils.NetworkUtil;
import com.softgarden.baselibrary.base.IBaseDisplay;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * RxJava2 转换器 用于网络加载数据 已实现功能有：
 * <p>
 * 1.检测有无网络
 * 2.加载网络时显示加载框 结束是隐藏
 * 3.控制RxJava生命周期，防止内存泄漏
 */
public class NetworkTransformer<T> implements ObservableTransformer<T, T> {
    private IBaseDisplay mView;

    public NetworkTransformer(IBaseDisplay mView) {
        if (mView == null) throw new RuntimeException("IBaseDisplay is not NULL");
        this.mView = mView;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (!NetworkUtil.isConnected(mView.getContext())) {
                        NetworkUtil.showNoNetWorkDialog(mView.getContext());
                        disposable.dispose();
                    } else {
                        mView.showProgressDialog();
                    }
                })
                .doFinally(() -> mView.hideProgressDialog())
                .map(tBaseBean -> {
                    if (tBaseBean != null) return tBaseBean;
                    //返回空数据时 抛出一个异常让CallBack处理
                    throw new RxJava2NullException();
                })
                .compose(mView.bindToLifecycle());

    }

}
