package freestar.vip.library.injector.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import freestar.vip.library.core.RemoteService;
import freestar.vip.library.injector.ContextLife;
import freestar.vip.library.injector.module.ApplicationModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getContext();



    Application injectApplication(Application application);

    RemoteService getRemoteService();

}
