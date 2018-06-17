package com.ddj.dudujia.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ddj.dudujia.common.GlideApp;
import com.ddj.dudujia.common.GlideOptions;
import com.first.basket.constants.Constants;

/**
 * Created by Luka on 2016/3/30.
 * E-mail:397308937@qq.com
 */
public class ImageUtils {
    public static void showImg(Context context, String url, ImageView ivImg) {
        if (!url.startsWith(Constants.Companion.getBASE_IMG_URL())) {
            url = Constants.Companion.getBASE_IMG_URL() + url;
        }
        GlideApp.with(context)
                .load(url)
                .apply(GlideOptions.centerInsideTransform())
//                .placeholder(R.mipmap.ic_placeholder)
//                .error(R.mipmap.ic_placeholder)
                .into(ivImg);
    }


    public static void showImg(Context context, int resourceId, ImageView ivImg, GlideOptions options) {
        GlideOptions opt = options;
        if (opt == null) {
            GlideOptions.fitCenterTransform();
            GlideApp.with(context)
                    .load(resourceId)
                    .into(ivImg);
        } else {
            GlideApp.with(context)
                    .load(resourceId)
                    .apply(options)
//                .placeholder(R.mipmap.ic_placeholder)
//                .error(R.mipmap.ic_placeholder)
                    .into(ivImg);
        }
    }


    public static void showImg(Context context, int resourceId, ImageView ivImg) {
        showImg(context, resourceId, ivImg, null);

    }

    public static void showImg(Context context, Uri uri, ImageView ivImg) {
        Glide.with(context).load(uri).into(ivImg);
    }

    public static void showImg(Context context, Bitmap bitmap, ImageView ivImg) {
        Glide.with(context).load(bitmap).into(ivImg);
    }

    public static void showImg(Context context, Drawable drawable, ImageView ivImg) {
        GlideApp.with(context)
                .load(drawable)
//                .placeholder(R.mipmap.ic_placeholder)
//                .error(R.mipmap.ic_placeholder)
                .into(ivImg);
    }
}
