package com.mirkowu.fastread.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.mirkowu.fastread.R;
import com.mirkowu.fastread.api.HostUrl;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.dialog.LoadingDialog;
import com.softgarden.baselibrary.utils.EmptyUtil;
import com.softgarden.baselibrary.utils.L;
import com.softgarden.baselibrary.utils.RxPermissionsUtil;
import com.softgarden.baselibrary.utils.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class ImageUtil {
    public static final String TAG = ImageUtil.class.getName();

    public static String getImageUrl(String url) {
        return HostUrl.IMG_BASE_URL + url;
    }


    /**
     * 检查url 是否拼接
     *
     * @param url
     * @return
     */
    public static String checkUrl(String url) {
        //有完整路径的url不用拼接 本地图片也不用
        if (url != null && (url.startsWith("http://")
                || url.startsWith("https://")
                || url.startsWith(FileCacheUtil.getRootDir().getAbsolutePath()))) {
        } else {
            url = getImageUrl(url);
        }

        return url;
    }

    /**
     * 检查url集合 是否拼接
     *
     * @param urlList
     * @return
     */
    public static List<String> checkUrl(List<String> urlList) {
        List<String> imgList = new ArrayList<>();
        if (EmptyUtil.isEmpty(urlList)) {
            return imgList;
        }
        for (String path : urlList) {
            imgList.add(checkUrl(path));
        }
        return imgList;
    }


    /**
     * 加载图片正方形图片
     * 默认图是正方形
     *
     * @param imageView
     * @param url
     */
    public static void load(ImageView imageView, @Nullable String url) {
        GlideApp.with(imageView.getContext())
                .load(checkUrl(url))
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }


    /**
     * 加载图片长方形图片
     * 默认图是长方形
     *
     * @param imageView
     * @param url
     */
    public static void loadRectangle(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(checkUrl(url))
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }


    /**
     * 加载头像
     *
     * @param imageView
     * @param url
     */
    public static void loadHeader(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(checkUrl(url))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /**
     * 加载大头像
     * 只是默认图不同
     *
     * @param imageView
     * @param url
     */
    public static void loadBigHeader(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(checkUrl(url))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }


    /**
     * 高斯模糊
     *
     * @param imageView
     * @param url
     */
    public static void loadBlur(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(checkUrl(url))
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                // .bitmapTransform(new BlurTransformation(imageView.getContext(), 1, 3))
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadBlur(ImageView imageView, String url, int radius, int sampling) {
        // radius "23":模糊度；sampling "4":图片缩放4倍后再进行模糊a
        GlideApp.with(imageView.getContext())
                .load(checkUrl(url))
                .dontAnimate()
                // .placeholder(R.mipmap.ic_launcher)
                // .bitmapTransform(new BlurTransformation(imageView.getContext(), radius, sampling))
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    public static void loadAsGif(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .asGif()
                .load(checkUrl(url))
                .thumbnail(0.5f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /**
     * 清除缓存
     *
     * @param context
     * @return
     */
    public static Observable<Boolean> clearCache(BaseActivity context) {
        return Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            GlideApp.get(context).clearDiskCache();
            e.onNext(true);
            e.onComplete();

        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> context.showProgressDialog())
                .doOnTerminate(context::hideProgressDialog)
                .compose(context.bindUntilEvent(ActivityEvent.DESTROY));

    }

    /**
     * 保存图片集合到sd卡
     *
     * @param context
     * @param imgList
     * @param isSilentLoad 是否静默下载
     */
    public static void loadImagesToSDCard(Activity context, List<String> imgList, boolean isSilentLoad) {
        if (imgList == null || imgList.isEmpty())
            return;
        //申请权限
        RxPermissionsUtil.requestStorage(context).subscribe(aBoolean -> {
            if (aBoolean) {
                LoadingDialog dialog = new LoadingDialog(context);
                if (!isSilentLoad) {
                    dialog.show();
                }
                Observable.create((ObservableOnSubscribe<List<String>>) e -> {
                    List<String> count = new ArrayList<String>();
                    for (String path : imgList) {
                        save2SDCard(context, ImageUtil.checkUrl(path))
                                .subscribe(bitmap -> {
                                    saveBitmap2File(context, bitmap, FileCacheUtil.getRootDir(), path.hashCode() + ".jpeg");
                                    count.add(path);
                                    if (!e.isDisposed())
                                        e.onNext(count);
                                }, throwable -> {
                                    if (!isSilentLoad) ToastUtil.s(path + "下载失败");
                                });
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(count -> {
                            if (count.size() == imgList.size()) {
                                if (!isSilentLoad) {
                                    dialog.dismiss();
                                    ToastUtil.l(String.format("图片已保存至%s文件夹内", FileCacheUtil.getRootDir()));
                                }
                            }
                        }, throwable -> {
                            if (!isSilentLoad) {
                                dialog.dismiss();
                                ToastUtil.s("下载失败");
                            }
                        });
            } else {//被拒绝 要去再次申请
                RxPermissionsUtil.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(aBoolean1 -> {
                            if (aBoolean1) {// 查询是否 应该提醒
                                RxPermissionsUtil.showPermissionDialog(context,
                                        "权限申请", "此功能需要读写存储权限，请授权",
                                        (dialog, which) -> loadImagesToSDCard(context, imgList, isSilentLoad));
                            } else {//选中了禁止以后提醒
                                RxPermissionsUtil.showLackPermissionDialog(context);
                            }
                        });
            }
        });
    }

    public static void loadImagesToSDCard(Activity activity, List<String> imageList) {
        loadImagesToSDCard(activity, imageList, false);
    }

    /**
     * 下载图片到本地SDCard
     *
     * @param context
     * @param imageUrl
     * @param saveName
     * @param isSilentLoad
     */
    public static void loadImageToSDCard(Activity context, String imageUrl, @NonNull String saveName, boolean isSilentLoad) {
        if (EmptyUtil.isEmpty(imageUrl))
            return;

        //申请权限
        RxPermissionsUtil.requestStorage(context).subscribe(aBoolean -> {
            if (aBoolean) {
                LoadingDialog dialog = new LoadingDialog(context);
                if (!isSilentLoad) {
                    dialog.show();
                }
                Observable.create((ObservableOnSubscribe<String>) e -> {
                    save2SDCard(context, ImageUtil.checkUrl(imageUrl))
                            .subscribe(bitmap -> {
                                saveBitmap2File(context, bitmap, FileCacheUtil.getRootDir(), saveName);
                                e.onNext(" ");
                            }/*, throwable -> {
                                if (!isSilentLoad) ToastUtil.s(R.string.load_img_failed);
                                e.onNext(" ");
                            }*/);
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(count -> {
                            if (!isSilentLoad) {
                                dialog.dismiss();
                                ToastUtil.l(String.format("图片已保存至%s文件夹内", FileCacheUtil.getRootDir()));
                            }
                        }, throwable -> {
                            if (!isSilentLoad) {
                                dialog.dismiss();
                                ToastUtil.s("保存失败");
                            }
                        });
            } else {//被拒绝 要去再次申请
                RxPermissionsUtil.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(aBoolean1 -> {
                            if (aBoolean1) {// 查询是否 应该提醒
                                RxPermissionsUtil.showPermissionDialog(context,
                                        "权限申请", "此功能需要读写存储权限，请授权",
                                        (dialog, which) -> loadImageToSDCard(context, imageUrl, saveName, isSilentLoad));
                            } else {//选中了禁止以后提醒
                                RxPermissionsUtil.showLackPermissionDialog(context);
                            }
                        });
            }
        });
    }

    public static void loadImageToSDCard(Activity activity, String imageUrl) {
        loadImageToSDCard(activity, imageUrl, imageUrl.hashCode() + ".jpeg", false);
    }


    /**
     * @param context
     * @param url     图片的下载地址
     */
    public static Observable<Bitmap> save2SDCard(final Context context, final String url) {
        L.d(TAG, "save2SDCard url=" + url);
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap bitmap = GlideApp.with(context)
                        .asBitmap()
                        .load(url)
                        .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();

                if (!e.isDisposed())
                    e.onNext(bitmap);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 将Bitmap保存为图片文件
     *
     * @param context
     * @param bitmap
     * @param dirFile
     * @param saveName
     */
    public static void saveBitmap2File(Context context, Bitmap bitmap, File dirFile, String saveName) {
        if (dirFile != null && dirFile.exists() && dirFile.isDirectory()) {

            File file = new File(dirFile, saveName);
            L.d(TAG, "saveBitmap2File  file.getAbsolutePath()=" + file.getAbsolutePath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));//刷新相册
        } else {
            throw new RuntimeException("the file is not exists or it is not directory !");
        }
    }

}
