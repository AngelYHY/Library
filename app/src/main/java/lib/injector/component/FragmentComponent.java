package lib.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import lib.injector.ContextLife;
import lib.injector.PerFragment;
import lib.injector.module.FragmentModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@PerFragment
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    @ContextLife("Application")
    Context getContext();

    @ContextLife("Activity")
    Context getActivityContext();


    Activity getActivity();

}
