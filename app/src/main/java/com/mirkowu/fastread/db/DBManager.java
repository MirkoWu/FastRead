package com.mirkowu.fastread.db;

import com.softgarden.baselibrary.BaseApplication;

/**
 * @author by DELL
 * @date on 2018/5/15
 * @describe
 */
public class DBManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile DBManager mInstance = null;

    private DBManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(BaseApplication.getInstance().getApplicationContext(), "bookshelf.db");
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

}
