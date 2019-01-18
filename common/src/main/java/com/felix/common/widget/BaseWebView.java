package com.felix.common.widget;


import com.felix.common.BuildConfig;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class BaseWebView extends WebView {

    public static final int LOAD_START = 1;

    public static final int LOAD_FINISH = 2;

    private Consumer<Integer> mLoadAction;

    private Consumer<String> mLoadTitle;

    public BaseWebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        init();
    }

    public BaseWebView(Context arg0) {
        super(arg0);
        init();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void destroy() {
        release();
        super.destroy();
    }

    private void release() {
        setWebChromeClient(null);
        setWebViewClient(null);
        mLoadAction = null;
        mLoadTitle = null;
    }

    private void init() {
        this.setWebChromeClient(bindWebChromeClient());
        this.setWebViewClient(bindWebViewClient());
        initWebViewSettings();
        setClickable(true);
    }

    protected void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setLoadsImagesAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(false);
        webSetting.setDisplayZoomControls(false);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSetting.setAppCachePath(getContext().getCacheDir().getPath());
        webSetting.setDatabasePath(getContext().getCacheDir().getPath());
        webSetting.setGeolocationDatabasePath(getContext().getCacheDir().getPath());
        webSetting.setDefaultTextEncodingName("utf-8");
        StringBuilder ua = new StringBuilder(webSetting.getUserAgentString());
        String tzUserAgent = "version_name/" + BuildConfig.VERSION_NAME + " version_code/"
                + BuildConfig.VERSION_CODE;
        ua.append(tzUserAgent);
        webSetting.setUserAgentString(ua.toString());
        Timber.d("WebView UserAgent : " + webSetting.getUserAgentString());
    }

    protected WebViewClient bindWebViewClient() {
        return new BaseWebViewClient();
    }

    protected WebChromeClient bindWebChromeClient() {
        return new BaseWebChromeClient();
    }

    public void setLoadAction(Consumer<Integer> loadAction) {
        mLoadAction = loadAction;
    }

    public void setLoadTitle(Consumer<String> loadTitle) {
        this.mLoadTitle = loadTitle;
    }

    protected class BaseWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            Timber.d("onPageStarted, time = " + System.currentTimeMillis());
            if (mLoadAction != null) {
                try {
                    mLoadAction.accept(LOAD_START);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            Timber.d("onPageFinished, time = " + System.currentTimeMillis());
            if (mLoadAction != null) {
                try {
                    mLoadAction.accept(LOAD_FINISH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected class BaseWebChromeClient extends WebChromeClient {

        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
            if (mLoadTitle != null && !TextUtils.isEmpty(s)) {
                try {
                    mLoadTitle.accept(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
            Timber.d("onProgressChanged : %d", i);
            super.onProgressChanged(webView, i);
        }
    }
}
