package org.suju.autoupdate.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import org.suju.autoupdate.entity.MetaModel;
import org.suju.autoupdate.listener.IMetaModelParse;
import org.suju.autoupdate.listener.OnClickListener;
import org.suju.autoupdate.util.ProgressNotificaionUtil;
import org.suju.autoupdate.util.RequestUtil;
import org.suju.autoupdate.view.IUpdateView;

/**
 * Created by suju on 16/11/2.
 */
public class ShowDialogUpdatePresenter implements IUpdatePresenter {

    private OnClickListener mOnClickListener;
    private IUpdateView mIUpdateView;
    private Handler mWorkHandler;
    private String mUpdateUrl;
    private String mMetaData;
    private String mDownloadUrl;
    private IMetaModelParse mIMetaModelParse;

    public ShowDialogUpdatePresenter(OnClickListener onClickListener, IMetaModelParse metaModelParse) {
        this.mIMetaModelParse = metaModelParse;
        this.mOnClickListener = onClickListener;
        this.mWorkHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        MetaModel metaModel = mIMetaModelParse.parse(msg.obj.toString());
                        if (metaModel != null) {
                            mDownloadUrl = metaModel.getDownloadUrl();
                            mIUpdateView.setViewData(metaModel);
                            mIUpdateView.showView();
                        }
                }
            }
        };
    }

    public void setUpdateUrl(String updateUrl) {
        this.mUpdateUrl = updateUrl;
    }

    public void setMetaData(String metaData) {
        this.mMetaData = metaData;
    }

    public void setView(IUpdateView updateView) {
        updateView.setPresenter(this);
        this.mIUpdateView = updateView;
    }

    @Override
    public void start() {
        if (!TextUtils.isEmpty(mMetaData)) {
            mWorkHandler.obtainMessage(0, mMetaData).sendToTarget();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = RequestUtil.get(mUpdateUrl);
                mWorkHandler.obtainMessage(0, data).sendToTarget();
            }
        }).start();
    }

    @Override
    public void onCancel() {
        mIUpdateView.hideView();
    }

    @Override
    public void onConfirm() {
        mIUpdateView.showUpdateView(mDownloadUrl);
        mIUpdateView.hideView();
    }
}
