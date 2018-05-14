package com.mirkowu.fastread.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.mirkowu.fastread.R;
import com.mirkowu.fastread.bean.ChapterBean;
import com.mirkowu.fastread.bean.ContentBean;
import com.mirkowu.fastread.network.NetworkTransformer;
import com.mirkowu.fastread.network.RetrofitClient;
import com.mirkowu.fastread.network.RxCallback;
import com.softgarden.baselibrary.base.BaseActivity;

import butterknife.BindView;

public class ReadActivity extends BaseActivity {
    public static void start(Context context, ChapterBean bean) {
        Intent starter = new Intent(context, ReadActivity.class);
        starter.putExtra(KEY_DATA, bean);
        context.startActivity(starter);
    }

    @BindView(R.id.mTvContent)
    TextView mTvContent;

    @BindView(R.id.mTvTitle)
    TextView mTvTitle;

    private ChapterBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    protected void initialize() {
        bean = (ChapterBean) getIntent().getSerializableExtra(KEY_DATA);
        mTvTitle.setText(bean.getTitle());

        loadChapter();
    }

    private void loadChapter() {
        RetrofitClient.getBookService()
                .chapterContent(bean.getLink())
                .compose(new NetworkTransformer<>(this))
                .subscribe(new RxCallback<ContentBean>() {
                    @Override
                    public void onSuccess(@Nullable ContentBean data) {
                        setBookData(data);
                    }
                });
    }

    private void setBookData(ContentBean data) {
        mTvContent.setText(data.getChapter().getBody());
    }
}
