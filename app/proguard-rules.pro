# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**

#auto-value-gson
# Retain generated classes that end in the suffix
-keepnames class **_GsonTypeAdapter

# Prevent obfuscation of types which use @GenerateTypeAdapter since the simple name
# is used to reflectively look up the generated adapter.
-keepnames @com.ryanharter.auto.value.gson.GenerateTypeAdapter class *

# Butterknife
-dontwarn butterknife.internal.**
-keep class butterknife.** { *; }
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.InjectView <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.OnClick <methods>;
    @butterknife.OnEditorAction <methods>;
    @butterknife.OnItemClick <methods>;
    @butterknife.OnItemLongClick <methods>;
    @butterknife.OnLongClick <methods>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#retrofit
-dontwarn com.squareup.okhttp.*
-keep class com.google.gson.**
-keep class com.google.inject.*
-keep class com.google.appengine.*
-keep class org.apache.http.*
-keep class org.apache.james.mime4j.*
-keep class javax.inject.*
-dontwarn rx.**
-keep class com.example.testobfuscation.**
-keepattributes Signature
-keep class sun.misc.Unsafe
-keep class retrofit.** { *; }
-keepattributes Annotation
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn retrofit.appengine.**
-dontwarn com.actionbarsherlock.**

#okio
-dontwarn okio.**

#okhttp
-dontwarn com.squareup.okhttp.**

#event bus 3.0
-keep class de.greenrobot.** { *; }

-keepclasseswithmembers class * {
  @de.greenrobot.event.Subscribe <methods>;
}

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

#fresco
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}


#ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
 -keep class * implements com.alibaba.android.arouter.facade.template.IProvider