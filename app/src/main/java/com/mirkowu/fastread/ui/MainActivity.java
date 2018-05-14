package com.mirkowu.fastread.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.fastread.R;
import com.mirkowu.fastread.base.RefreshActivity;
import com.mirkowu.fastread.bean.BookDetailBean;
import com.mirkowu.fastread.utils.ImageUtil;
import com.softgarden.baselibrary.base.BaseRVAdapter;
import com.softgarden.baselibrary.base.BaseRVHolder;

public class MainActivity extends RefreshActivity {


    private BaseRVAdapter<BookDetailBean> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("快读")
                .addLeftText("设置", v -> {
                    SettingActivity.start(this);
                })
                .addRightText("搜索", v -> {
                    SearchActivity.start(this);
                });
    }

    @Override
    protected void initialize() {
        getToolbar().hideBackButton();
        initRecyclerView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new BaseRVAdapter<BookDetailBean>(R.layout.item_bookshelf) {
            @Override
            public void onBindVH(BaseRVHolder holder, BookDetailBean data, int i) {
                ImageUtil.load(holder.getView(R.id.mIvCover), data.getCover());
                holder.setText(R.id.mTvName, data.getTitle());
            }
        };
    }


    @Override
    public void onRefresh() {

    }
}
