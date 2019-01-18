package com.felix.mashup.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.stetho.Stetho;
import com.felix.common.uitls.FrescoCacheParams;
import com.felix.common.uitls.FrescoUtil;
import com.felix.common.uitls.io.CacheUtils;
import com.felix.common.uitls.log.LogToFileTree;
import com.felix.common.uitls.model.PrefDeviceUtils;
import com.felix.common.uitls.model.PrefUtils;
import com.felix.common.uitls.net.ApiErrorProcessor;
import com.felix.common.uitls.ui.ScreenUtils;
import com.felix.common.uitls.ui.ToastUtils;
import com.felix.mashup.app.di.AppConfigModule;
import com.felix.mashup.app.di.ApplicationComponent;
import com.felix.mashup.app.di.ApplicationModule;
import com.felix.mashup.app.di.DaggerApplicationComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.greenrobot.eventbus.EventBus;

import android.graphics.Bitmap;
import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;

import dagger.Lazy;
import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by chaichuanfa on 2018/1/10.
 */

public class App extends MultiDexApplication {

    @Inject
    Lazy<EventBus> mBus;

    @Inject
    Lazy<OkHttpClient> mOkHttpClient;

    private static App mInstance;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        logCollect();
        initUtils();
        injectComponent();
        ApiErrorProcessor.init(this, mBus.get());
        initARouter();
        initFresco();

        //Fragmentation
        Fragmentation.builder()
                .stackViewMode(Fragmentation.SHAKE)
                .debug(AppConfigModule.IS_DEBUG).install();
    }

    private void initFresco() {
        // https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
        OkHttpClient.Builder frescoClient = mOkHttpClient.get().newBuilder();
        if (AppConfigModule.SHOW_LOG) {
            frescoClient.addInterceptor(new HttpLoggingInterceptor(
                    message -> Timber.d("OkHttp: " + message))
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS));
        }
        MemoryTrimmableRegistry memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        memoryTrimmableRegistry.registerMemoryTrimmable(trimType -> {
            Timber.d("MemoryTrimmabl, type = " + trimType);
            if (trimType == MemoryTrimType.OnCloseToDalvikHeapLimit
                    || trimType == MemoryTrimType.OnSystemLowMemoryWhileAppInBackground
                    || trimType == MemoryTrimType.OnAppBackgrounded) {
                //清除内存缓存
                FrescoUtil.clearMemoryCaches();
            }
        });

        //其他图片
        DiskCacheConfig diskMainCacheConfig = DiskCacheConfig.newBuilder(getInstance())
                .setBaseDirectoryPath(getCacheDir())
                .setBaseDirectoryName(CacheUtils.FRESCO_CACHE_DIR)
                .setMaxCacheSize(100 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(50 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(25 * ByteConstants.MB)
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setNetworkFetcher(new OkHttpNetworkFetcher(frescoClient.build()))
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setDownsampleEnabled(true)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
                .setMainDiskCacheConfig(diskMainCacheConfig)
                .setBitmapMemoryCacheParamsSupplier(new FrescoCacheParams(this))
                .setResizeAndRotateEnabledForNetwork(true)
                .build();

        Fresco.initialize(this, config);
    }

    private void initUtils() {
        ToastUtils.setContext(this);
        ScreenUtils.setContext(this);
        PrefDeviceUtils.setContext(this);
        PrefUtils.setContext(this);
        AndroidThreeTen.init(this);
    }

    private void logCollect() {
        if (AppConfigModule.IS_TEST) {
            Timber.plant(new LogToFileTree(this, getPackageName()));
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(
                            Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }

    private void initARouter() {
        if (AppConfigModule.SHOW_LOG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void injectComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getInstance()))
                .appConfigModule(new AppConfigModule(""))
                .build();
        mApplicationComponent.inject(this);
    }

    public static App getInstance() {
        return mInstance;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
