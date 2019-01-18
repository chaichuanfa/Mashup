package com.felix.common.uitls.databinding;

import com.facebook.drawee.view.SimpleDraweeView;

import android.databinding.BindingAdapter;
import android.net.Uri;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public class FrescoBindingAdapter {

    @BindingAdapter(value = {"android:imageUrl"}, requireAll = false)
    public static void setImageURI(SimpleDraweeView simpleDraweeView,
            final String url) {
        simpleDraweeView.setImageURI(Uri.parse(url));
    }

}
