package com.mirkowu.fastread.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.fastread.R;
import com.mirkowu.fastread.base.RefreshActivity;
import com.mirkowu.fastread.bean.BookBean;
import com.mirkowu.fastread.bean.SearchBookBean;
import com.mirkowu.fastread.network.NetworkTransformer;
import com.mirkowu.fastread.network.RetrofitClient;
import com.mirkowu.fastread.network.RxCallback;
import com.mirkowu.fastread.utils.ImageUtil;
import com.softgarden.baselibrary.base.BaseRVAdapter;
import com.softgarden.baselibrary.base.BaseRVHolder;
import com.softgarden.baselibrary.utils.L;

import butterknife.BindView;

public class SearchActivity extends RefreshActivity implements BaseQuickAdapter.OnItemClickListener {
    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }


    @BindView(R.id.mIvBack)
    ImageView mIvBack;
    @BindView(R.id.mEdtSearch)
    EditText mEdtSearch;

    private BaseRVAdapter<BookBean> mAdapter;
    private String searchKeyword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    @Override
    protected void initialize() {
        initRefreshLayout();
        initRecyclerView();
        addItemDecoration();
        mAdapter = new BaseRVAdapter<BookBean>(R.layout.item_search_book) {
            @Override
            public void onBindVH(BaseRVHolder holder, BookBean data, int position) {
                ImageUtil.load(holder.getView(R.id.mIvCover), data.getCover());
                holder.setText(R.id.mTvAuthor, data.getAuthor())
                        .setText(R.id.mTvType, data.getCat())
                        .setText(R.id.mTvLastChapter, data.getLastChapter())
                        .setText(R.id.mTvDes, data.getShortIntro());
            }
        };
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mIvBack.setOnClickListener(v -> onBackPressed());
        mEdtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {//软键盘的搜索按钮
                searchKeyword = mEdtSearch.getText().toString().trim();
                onRefresh();
            }
            return false;
        });
    }

    private void searchBook() {
        int start = (mPage - 1) * PAGE_COUNT;
        RetrofitClient.getBookService()
                .search(searchKeyword, start, PAGE_COUNT)
                .compose(new NetworkTransformer<>(this))
                .subscribe(new RxCallback<SearchBookBean>() {
                    @Override
                    public void onSuccess(@Nullable SearchBookBean data) {
                        L.d("onSuccess  "+(data==null)+data.isOk()+data.getTotal());
                        L.d("onSuccess  "+(data.getBooks()==null));
                        mAdapter.setNewData(data.getBooks());
                      //  setLoadMore(mAdapter, data.getBooks());
                    }
                });
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        searchBook();
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        searchBook();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BookBean bean = mAdapter.getItem(position);
        BookDetailActivity.start(this, bean.get_id());
    }
}
