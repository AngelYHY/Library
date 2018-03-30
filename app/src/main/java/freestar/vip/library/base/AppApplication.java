package freestar.vip.library.base;

import android.app.Application;

import com.rey.material.app.ThemeManager;
import com.squareup.leakcanary.LeakCanary;

import freestar.vip.library.injector.component.ApplicationComponent;
import freestar.vip.library.injector.component.DaggerApplicationComponent;
import freestar.vip.library.injector.module.ApplicationModule;
import vip.freestar.mylogger.Logger;

/**
 * Created by freestar on 2017/score/9 0009.
 */

public class AppApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ThemeManager.init(this, 2, 0, null);
        init();
        Logger.init();
        LeakCanary.install(this);
    }

    private void init() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.injectApplication(this);

        enabledStrictMode();
    }

    private void enabledStrictMode() {
        // SDK_INT 系统版本   GINGERBREAD 版本号 9
//        if (SDK_INT >= GINGERBREAD) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
//                    .detectAll() //
//                    .penaltyLog() //打印logcat
//                    .penaltyDeath() //
//                    .build());
//        }
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
