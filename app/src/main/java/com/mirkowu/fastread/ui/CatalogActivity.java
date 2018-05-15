package com.mirkowu.fastread.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.fastread.R;
import com.mirkowu.fastread.base.RefreshActivity;
import com.mirkowu.fastread.bean.BookChaptersBean;
import com.mirkowu.fastread.bean.ChapterBean;
import com.mirkowu.fastread.network.NetworkTransformer;
import com.mirkowu.fastread.network.RetrofitClient;
import com.mirkowu.fastread.network.RxCallback;
import com.softgarden.baselibrary.utils.EmptyUtil;

import java.util.Collections;
import java.util.List;

public class CatalogActivity extends RefreshActivity implements BaseQuickAdapter.OnItemClickListener {

    public static void start(Context context, String book_id) {
        Intent starter = new Intent(context, CatalogActivity.class);
        starter.putExtra(KEY_DATA, book_id);
        context.startActivity(starter);
    }

    private CatalogAdapter mAdapter;
    private String book_id;
    private List<ChapterBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("全部章节")
                .addRightImage(R.mipmap.icon_sort, v -> {
                    if (EmptyUtil.isNotEmpty(list)) {
                        Collections.reverse(list);
                        mAdapter.setNewData(list);
                    }
                });
    }

    @Override
    protected void initialize() {
        initRecyclerView();
        addItemDecoration();
        mAdapter = new CatalogAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        book_id = getIntent().getStringExtra(KEY_DATA);
        loadChapters();
    }

    private void loadChapters() {
        RetrofitClient.getBookService()
                .bookChapters(book_id)
                .compose(new NetworkTransformer<>(this))
                .subscribe(new RxCallback<BookChaptersBean>() {
                    @Override
                    public void onSuccess(@Nullable BookChaptersBean data) {
                        if (data != null && data.getMixToc() != null) {
                            list = data.getMixToc().getChapters();
                            //Collections.reverse(list);
                            mAdapter.setNewData(list);
                        }
                    }
                });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ChapterBean bean = mAdapter.getItem(position);
        ReadActivity.start(this, bean);
    }

    @Override
    public void onRefresh() {

    }

}
