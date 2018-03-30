package freestar.vip.library.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import freestar.vip.library.injector.ContextLife;
import freestar.vip.library.injector.PerActivity;
import freestar.vip.library.injector.module.ActivityModule;

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
