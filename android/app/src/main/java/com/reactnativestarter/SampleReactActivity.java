package com.reactnativestarter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import com.facebook.react.PackageList;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.soloader.SoLoader;
import com.microsoft.codepush.react.*;
import java.util.List;

// Existing Apps - https://reactnative.dev/docs/integration-with-existing-apps -
// Code Push Getting Started - https://docs.microsoft.com/en-us/appcenter/distribution/codepush/rn-get-started
// Code Push - https://docs.microsoft.com/en-us/appcenter/distribution/codepush/rn-deployment#multi-deployment-testing
// Code Push CLI - https://docs.microsoft.com/en-us/appcenter/distribution/codepush/cli
// Communication through properties - https://reactnative.dev/docs/communication-android
// Communication through native modules - https://reactnative.dev/docs/native-modules-android
// Crash reporting -

public class SampleReactActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);
        List<ReactPackage> packages = new PackageList(getApplication()).getPackages();
        packages.add(new PreferencePackage()); // <-- Add this line with your package name.

        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(new MyReactNativePackage());
        // Remember to include them in `settings.gradle` and `app/build.gradle` too.
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .setJSBundleFile(CodePush.getJSBundleFile())
//                .setJSBundleFile("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackages(packages)
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        Bundle initialProperties = new Bundle();
        initialProperties.putString("base_url", "https://www.google.com");
        initialProperties.putString("locale","EN");
        initialProperties.putString("auth_token", "xxxx");
        initialProperties.putString("id",getIntent().getStringExtra("id"));
        mReactRootView.startReactApplication(mReactInstanceManager, "ReactNativeStarter",initialProperties);
        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}