package com.mirkowu.fastread.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.fastread.R;
import com.mirkowu.fastread.base.ToolbarActivity;

import butterknife.BindView;

public class SettingActivity extends ToolbarActivity {
    @BindView(R.id.llRoot)
    LinearLayout llRoot;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle(R.string.setting);
    }

    @Override
    protected void initialize() {
        getToolbar().addRightText("添加", v -> {
        });
    }


}
