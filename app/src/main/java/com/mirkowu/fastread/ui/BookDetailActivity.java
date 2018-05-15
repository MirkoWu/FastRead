package com.mirkowu.fastread.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.fastread.R;
import com.mirkowu.fastread.base.ToolbarActivity;
import com.mirkowu.fastread.bean.BookChaptersBean;
import com.mirkowu.fastread.bean.BookDetailBean;
import com.mirkowu.fastread.bean.ChapterBean;
import com.mirkowu.fastread.db.BookDetailBeanDao;
import com.mirkowu.fastread.db.DBManager;
import com.mirkowu.fastread.network.NetworkTransformer;
import com.mirkowu.fastread.network.RetrofitClient;
import com.mirkowu.fastread.network.RxCallback;
import com.mirkowu.fastread.utils.ImageUtil;
import com.softgarden.baselibrary.widget.ColorDividerDecoration;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailActivity extends ToolbarActivity implements BaseQuickAdapter.OnItemClickListener {
    public static void start(Context context, String book_id) {
        Intent starter = new Intent(context, BookDetailActivity.class);
        starter.putExtra(KEY_DATA, book_id);
        context.startActivity(starter);
    }

    @BindView(R.id.mIvCover)
    ImageView mIvCover;
    @BindView(R.id.mTvAuthor)
    TextView mTvAuthor;
    @BindView(R.id.mTvType)
    TextView mTvType;
    @BindView(R.id.mTvFrom)
    TextView mTvFrom;
    @BindView(R.id.mTvTime)
    TextView mTvTime;
    @BindView(R.id.mTvDes)
    TextView mTvDes;
    @BindView(R.id.mBtnAddBookshelf)
    TextView mBtnAddBookshelf;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private CatalogAdapter mAdapter;
    private String book_id;
    private BookDetailBeanDao bookDao;
    private BookDetailBean bookDetailBean;
    private boolean isAddBookShelf;//是否已加入书架

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder;
    }

    @Override
    protected void initialize() {
        book_id = getIntent().getStringExtra(KEY_DATA);
        bookDao = DBManager.getInstance().getNewSession().getBookDetailBeanDao();
        isAddBookShelf = bookDao.load(book_id) != null;
        initRecyclerView();
        loadBookDetail();
        loadChapters();

    }

    private void initRecyclerView() {
        mAdapter = new CatalogAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.addItemDecoration(new ColorDividerDecoration(this));
        mRecyclerView.setNestedScrollingEnabled(false);//
    }

    private void loadBookDetail() {
        RetrofitClient.getBookService()
                .detail(book_id)
                .compose(new NetworkTransformer<>(this))
                .subscribe(new RxCallback<BookDetailBean>() {
                    @Override
                    public void onSuccess(@Nullable BookDetailBean data) {
                        setBookData(data);
                    }
                });
    }

    private void loadChapters() {
        RetrofitClient.getBookService()
                .bookChapters(book_id)
                .compose(new NetworkTransformer<>(this))
                .subscribe(new RxCallback<BookChaptersBean>() {
                    @Override
                    public void onSuccess(@Nullable BookChaptersBean data) {
                        if (data != null && data.getMixToc() != null) {
                            List<ChapterBean> list = data.getMixToc().getChapters();
                            Collections.reverse(list);
                            if (list.size() < 20) {
                                mAdapter.setNewData(data.getMixToc().getChapters());
                            } else {//最新20章
                                List<ChapterBean> lastList = list.subList(0, 20);
                                mAdapter.setNewData(lastList);
                            }
                        }
                    }
                });
    }

    private void setBookData(BookDetailBean data) {
        this.bookDetailBean = data;
        getToolbar().setTitle(data.getTitle());
        ImageUtil.load(mIvCover, data.getCover());
        mTvAuthor.setText(data.getAuthor());
        mTvType.setText(data.getCat());
        mTvFrom.setText(data.getCopyright());
        mTvTime.setText(data.getUpdated());
        mTvDes.setText(data.getLongIntro());
        if (isAddBookShelf) {
            mBtnAddBookshelf.setText("移除书架");
        } else {
            mBtnAddBookshelf.setText("加入书架");
        }

    }

    @OnClick({R.id.mBtnStartRead, R.id.mBtnCatalog, R.id.mBtnAddBookshelf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtnStartRead://开始阅读
                break;
            case R.id.mBtnCatalog://目录
                CatalogActivity.start(this, book_id);
                break;
            case R.id.mBtnAddBookshelf://加入/移除 书架
                if (isAddBookShelf) {
                    bookDao.delete(bookDetailBean);
                    mBtnAddBookshelf.setText("加入书架");
                    isAddBookShelf = false;
                } else {
                    bookDao.insert(bookDetailBean);
                    mBtnAddBookshelf.setText("移除书架");
                    isAddBookShelf = true;
                }
                break;

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ChapterBean bean = mAdapter.getItem(position);
        ReadActivity.start(this, bean);
    }
}
