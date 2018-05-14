package com.mirkowu.fastread.ui;

import com.mirkowu.fastread.R;
import com.mirkowu.fastread.bean.ChapterBean;
import com.softgarden.baselibrary.base.BaseRVAdapter;
import com.softgarden.baselibrary.base.BaseRVHolder;

/**
 * @author by DELL
 * @date on 2018/5/14
 * @describe
 */
public class CatalogAdapter extends BaseRVAdapter<ChapterBean>{
    public CatalogAdapter() {
        super(R.layout.item_catalog);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, ChapterBean data, int position) {
        holder.setText(R.id.mTvTitle,data.getTitle());
    }
}
