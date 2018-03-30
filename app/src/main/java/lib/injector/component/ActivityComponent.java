package lib.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import lib.injector.ContextLife;
import lib.injector.PerActivity;
import lib.injector.module.ActivityModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

}
