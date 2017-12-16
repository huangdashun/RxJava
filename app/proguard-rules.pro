# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/rqg/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in decode.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface


# ---------------------android sdk 推荐混淆配置-------------------#
# 混淆时所采用的算法
#-optimizations !code/allocation/variable
#,!code/simplification/cast,!field/*,!class/merging/*,!code/allocation/variable
#proguard对代码进行迭代优化的次数
-optimizationpasses 3
#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
#混淆时是否做预校验
-dontpreverify
-repackageclasses ''
-dontskipnonpubliclibraryclasses

#-dontoptimize  #不优化
#-dontobfuscate  #不混淆输入的类文件
-ignorewarnings #忽略警告
#-dontusemixedcaseclassnames #是否使用大小写混合
#指定不去忽略非公共的库类 --> （打开 则关闭混淆第三方jar类以及成员 & 关闭 则混淆第三方jar包类以及成员）
#-dontskipnonpubliclibraryclasses
#-dontskipnonpubliclibraryclassmembers

#混淆时是否记录日志
-verbose
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
 #混淆前后的映射
-printmapping mapping.txt

-keep public class android.net.http.SslError

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError

#
#-renamesourcefileattribute SourceFile
#保护给定的可选属性，例如 LineNumberTable, LocalVariableTable, SourceFile, Deprecated, Synthetic, Signature, and
-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes Exceptions,Signature,InnerClasses

#保持以下类不被混淆
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}



# json model
-keep class cn.ginshell.bong.model.** { *;}
-keep class cn.ginshell.bong.api.params.** { *;}

-keepclassmembers class * extends android.webkit.WebChromeClient{
       public void openFileChooser(...);
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# ---------------------android sdk 推荐的混淆配置结束-------------------#


# 枚举保持 see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}


#-keep class java.util.ArrayList { *;}

#保留系统组件及其子类：
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}
-keep public class * extends android.support.v4.app.Fragment
-keepclassmembers class * extends android.support.v4.app.Fragment {
   public <methods>;
}
#做动画时，view的包装类
-keep public class cn.ginshell.bong.ui.fragment.pro.wrap.ViewWrapper{
    void set*(***);
      *** get*();
}

-keep class com.android.internal.telephony.ITelephony { *; }

-keep public abstract interface com.android.internal.telephony.ITelephony{
    public protected <methods>;
}

-keep class !cn.ginshell.bong.**,!cn.ginshell.bong.**{*;}

#-keep class cn.ginshell.bong.ui.view.**{*;}

############################################################
#butterknife
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
############################################################

############################################################
#           retrofit 2
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

############################################################

############################################################
#           GreenDao
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
############################################################


############################################################
#umeng
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

############################################################




-dontnote com.umeng.**
-dontnote com.tencent.**
-dontnote android.support.**
-dontnote com.google.gson.**
-dontnote com.readystatesoftware.systembartint.**
-dontnote com.sina.weibo.**
-dontnote com.ta.utdid2.android.**
-dontnote retrofit2.**
-dontnote com.google.vending.licensing.ILicensingService
-dontnote **ILicensingService
-dontnote org.**
-dontnote android.net.**
-dontnote com.android.**

############################################################

-dontwarn sun.misc.Unsafe
-dontwarn butterknife.**
-dontwarn okio.**
-dontwarn org.android.agoo.ut.impl.**
-dontwarn com.amap.api.**
-dontwarn retrofit2.**
-dontwarn org.apache.**
-dontwarn com.tencent.**

############################################################
#databingding
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
############################################################

-dontwarn com.google.common.**
-keep class vi.com.** {*;}
-keep class com.umeng.** {*;}
-dontwarn com.umeng.**
-keep class com.amap.** {*;}
-keep class com.autonavi.**{*;}


############################################################
#fresco

# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
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

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

############################################################
############################################################


#remove log
#-assumenosideeffects class android.util.Log { *; }
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
}
#    public static int e(...);