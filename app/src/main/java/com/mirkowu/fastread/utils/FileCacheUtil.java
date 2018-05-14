package com.mirkowu.fastread.utils;

import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.softgarden.baselibrary.BaseApplication;
import com.softgarden.baselibrary.utils.ToastUtil;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by zhaoyong on 2016/6/21.
 * Glide缓存工具类
 */
public class FileCacheUtil {

    public static final String APP_PATH = "BaseLibrary";
    public static final String IMAGE_PATH = "imageCache";
    private final String IMAGE_EXTERNAL_CATCH_DIR = BaseApplication.getInstance().getExternalCacheDir() + "/" + IMAGE_PATH;


    /**
     * 获取应用SD卡缓存的根路径
     *
     * @return
     */
    public static File getRootDir() {
        File RootDir;
        String status = Environment.getExternalStorageState();
        //判断是否有SD卡
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            RootDir = new File(Environment.getExternalStorageDirectory(), APP_PATH);
            if (!RootDir.exists())
                RootDir.mkdirs();
            return RootDir;
        } else {
            ToastUtil.s("没有发现SD卡");
        }
        return null;
    }


    /**
     * 保存bitmap为图片
     *
     * @param bitmap   要保存的bitmap
     * @param dirFile  要保存的文件夹
     * @param saveName 要保存的文件名称
     */
//    public static void saveBitmap2File(Context context, Bitmap bitmap, File dirFile, String saveName) {
//        ImageUtil.saveBitmap2File(context,bitmap, dirFile, saveName);
//        // 通知图库更新
//      //  BaseApplication.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(dirFile, saveName))));
//    }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(BaseApplication.getInstance()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(BaseApplication.getInstance()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(BaseApplication.getInstance()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache() {
        clearImageDiskCache();
        clearImageMemoryCache();
        deleteFolderFile(IMAGE_EXTERNAL_CATCH_DIR, true);
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize() {
        try {
            return getFormatSize(getFolderSize(new File(IMAGE_EXTERNAL_CATCH_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        //        if (kiloByte < 1) {
        //            return size + "Byte";
        //        }

        double megaByte = kiloByte / 1024;
        //        if (megaByte < 1) {
        //            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
        //            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        //        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}