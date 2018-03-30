package freestar.vip.library.injector.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import freestar.vip.library.injector.ContextLife;
import freestar.vip.library.injector.PerActivity;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return mActivity;
    }

}
