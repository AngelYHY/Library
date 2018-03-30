package freestar.vip.library.injector.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import freestar.vip.library.injector.ContextLife;
import freestar.vip.library.injector.PerFragment;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    Context provideContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    Fragment provideFragment() {
        return mFragment;
    }


}
