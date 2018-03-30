package lib.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.net.UnknownHostException;

import freestar.freelibrary.base.BaseAppCompatFragment;
import freestar.freelibrary.base.IPresenter;
import freestar.freelibrary.base.IView;
import freestar.freelibrary.util.netstatu.NetUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lib.injector.component.DaggerFragmentComponent;
import lib.injector.component.FragmentComponent;
import lib.injector.module.FragmentModule;

/**
 * Created by jact on 2016/station/16.
 */
public abstract class BaseFragment extends BaseAppCompatFragment implements IView {
    protected MaterialDialog dialog;
    protected FragmentComponent mFragmentComponent;
//    protected Toolbar mToolbar;
    //    protected EmptyView mEmptyView;
    public int page = 1;
    protected final static int PAGE_SIZE = 5;

    protected IPresenter mIPresenter;

    @Override
    protected void initInjector() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(((AppApplication) getActivity().getApplication()).getComponent())
                .build();

//        mToolbar = ButterKnife.findById(getActivity(), R.id.common_toolbar);
//        if (null != mToolbar) {
//            mToolbar.setTitle("");
//            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    protected int getScreenHeight() {
        return getActivity().findViewById(android.R.id.content).getHeight();
    }

    /*@Override
    public <T> Observable bindToLifecycle(Observable<T> observable) {
        return observable.compose(this.<T>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }*/

    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void showException(Throwable ex, int flag) {
        //toggleShowError(true, msg, null);
        if (flag == 1) {
            showFail(1);
        } else if (flag == 2) {
            showFail(2);
        } else {
            if (ex instanceof Exception) {
                //无网络
                if (ex instanceof UnknownHostException) {
//                    if (mEmptyView != null) {
//                        mEmptyView.showError("");
//                    }
                } else {
                    showMsg(mContext.getString(freestar.freelibrary.R.string.common_error_service));
                }
            }
        }
    }

    @Override
    public void showEmpty() {
//        if (mEmptyView != null) {
//            mEmptyView.showEmpty();
//        }
    }

    @Override
    public void showEmpty(String msg) {
//        if (mEmptyView != null) {
//            mEmptyView.showEmpty();
//        }
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void showLoading() {
//        if (mEmptyView != null) {
//            mEmptyView.showLoading();
//        }
    }

    @Override
    public void hide() {
//        if (mEmptyView != null) {
//            mEmptyView.hide();
//        }
    }

    @Override
    public void showMsg(String msg) {
//        ToastUtils.LongToast(getActivity().getApplicationContext(), msg);
//        MyToast.shortToast(getActivity().getApplicationContext(), msg);
    }

    @Override
    public void showDialog() {
        showDialog(getString(freestar.freelibrary.R.string.loading_default_text));
    }

    @Override
    public void showDialog(String content) {
        if (dialog == null) {
            dialog = new MaterialDialog.Builder(getActivity())
                    .progress(true, 0)
                    .content(content)
                    .canceledOnTouchOutside(false)
                    .build();
            dialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showFail(int flag) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIPresenter != null) {
            mIPresenter.detachView();
        }
        /*RefWatcher refWatcher = ((AppApplication)getActivity().getApplication()).getRefWatcher(getActivity());
        refWatcher.watch(this);*/
    }

}
