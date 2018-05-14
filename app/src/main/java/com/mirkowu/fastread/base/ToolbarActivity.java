package com.mirkowu.fastread.base;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.fastread.R;
import com.mirkowu.statusbarutil.StatusBarUtil;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.base.IBasePresenter;
import com.softgarden.baselibrary.utils.BaseSPManager;
import com.softgarden.baselibrary.utils.ContextUtil;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public abstract class ToolbarActivity<P extends IBasePresenter> extends BaseActivity<P> {
    private BaseToolbar mBaseToolbar;


    @Override
    protected void initContentView() {

        setSupportToolbar();

        //设置沉浸式透明状态栏
        //StatusBarUtil.setTransparent(this);
        StatusBarUtil.setStatusBarColor(this, ContextUtil.getColor(R.color.colorPrimary));

        //ButterKnife
        unbinder = ButterKnife.bind(this);

        //非夜间模式 要开启亮色模式
        // setStatusBarLightMode();
    }

    private void setSupportToolbar() {
        /*** 这里可以对Toolbar进行统一的预设置 */
        BaseToolbar.Builder builder
                = new BaseToolbar.Builder(getContext())
                .setBackButton(R.mipmap.back)//统一设置返回键
                //    .setStatusBarColor(ContextUtil.getColor(R.color.colorPrimary))//统一设置颜色
                .setBackgroundColor(ContextUtil.getColor(R.color.colorPrimary))
                .setSubTextColor(Color.WHITE)
                .setTitleTextColor(Color.WHITE);

        builder = setToolbar(builder);
        if (builder != null) {
            mBaseToolbar = builder.build();
        }
        if (mBaseToolbar != null) {
            //添加Toolbar
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(mBaseToolbar);
            View mView = getLayoutInflater().inflate(getLayoutId(), layout, false);
            layout.addView(mView);
            setContentView(layout);
            //将toolbar设置为actionbar
            setSupportActionBar(mBaseToolbar);
        } else {
            setContentView(getLayoutId());
        }
    }


    @Override
    public void setStatusBarLightMode() {
        if (!BaseSPManager.isNightMode()) {
            if (StatusBarUtil.setStatusBarLightModeWithNoSupport(this, true)) {
                getToolbar().hideStatusBar();
            }
        }
    }

    public BaseToolbar getToolbar() {
        return mBaseToolbar;
    }


    public void showToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.GONE);
    }


    /**
     * 不需要toolbar的 可以不用管
     *
     * @return
     */
    @Nullable
    protected abstract BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder);
}
