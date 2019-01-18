package com.felix.common.uitls;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.DefaultExecutorSupplier;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.felix.common.uitls.ui.ScreenUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;


/**
 * Created by chaichuanfa on 2019/1/18
 */
public final class FrescoUtil {

    private static final DefaultExecutorSupplier DEFAULT_EXECUTOR_SUPPLIER
            = new DefaultExecutorSupplier(Math.min(2, Runtime.getRuntime().availableProcessors()));

    // 自己发送图片, 很可能当时 IM 消息里面是没有宽高的, 所以搞一个默认宽高
    private static final int DEFAULT_WIDTH = 458;

    private static final int DEFAULT_HEIGHT = 300;

    private FrescoUtil() {
        // no instance
    }

    public static void loadWithSize(SimpleDraweeView draweeView, Uri uri, int width, int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .setProgressiveRenderingEnabled(true)
                .setRotationOptions(RotationOptions.autoRotate())
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(controller);
    }

    public static void loadWithSizeNoPlay(SimpleDraweeView draweeView, Uri uri, int width,
            int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .setProgressiveRenderingEnabled(true)
                .setRotationOptions(RotationOptions.autoRotate())
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
    }

    /**
     * 获取uri
     */
    public static Uri getResourceUri(int resId, String packageName) {
        return Uri.parse("res://" + packageName + "/" + resId);
    }

    public static void clearMemoryCaches() {
        if (Fresco.hasBeenInitialized()) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            if (imagePipeline != null) {
                imagePipeline.clearMemoryCaches();
            }
        }
    }

    public static void clearUriFromMemory(Uri uri) {
        if (Fresco.hasBeenInitialized()) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            if (imagePipeline != null) {
                imagePipeline.evictFromMemoryCache(uri);
            }
        }
    }

    public static Bitmap decodeFile(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        int screenWidth = ScreenUtils.getScreenWidth();
        int screenHeight = ScreenUtils.getScreenHeight();
        int dx = picWidth / screenWidth;
        int dy = picHeight / screenHeight;
        int scale = 1;
        if (dx >= dy && dy >= 1) {
            scale = dx;
        }
        if (dy > dx && dx >= 1) {
            scale = dy;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        return decodeFile(path, options);
    }

    public static Bitmap decodeFile(String path, BitmapFactory.Options op) {
        try {
            return BitmapFactory.decodeFile(path, op);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
