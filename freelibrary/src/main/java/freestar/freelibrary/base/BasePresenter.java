package freestar.freelibrary.base;


/**
 * Created by codeest on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        //unSubscribe();
    }

}
