package com.horen.base.rx;

import android.app.Activity;
import android.content.Context;

import com.horen.base.R;
import com.horen.base.app.BaseApplication;
import com.horen.base.util.NetWorkUtils;
import com.horen.base.widget.LoadingDialog;

import io.reactivex.observers.DisposableObserver;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

/********************使用例子********************/
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new BaseObserver<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }

@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;
    private LoadingDialog loadingDialog;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public BaseObserver(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public BaseObserver(Context context) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), true);
    }

    public BaseObserver(Context context, boolean showDialog) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), showDialog);
    }

    public BaseObserver(Context context, String msg) {
        this(context, msg, true);
    }

    @Override
    public void onComplete() {
        if (showDialog)
            loadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                loadingDialog = new LoadingDialog();
                loadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
            loadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            _onError(BaseApplication.getAppContext().getString(R.string.error_please_check_network));
        }
        //服务器
        else if (e instanceof ServerException) { // 得到自定义Error，取得失败信息
            ServerException err = (ServerException) e;
            _onError(err.getMessage());
        }
        //其它(请求超时)
        else {
            _onError("服务器不稳定，请稍候再试。");
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}