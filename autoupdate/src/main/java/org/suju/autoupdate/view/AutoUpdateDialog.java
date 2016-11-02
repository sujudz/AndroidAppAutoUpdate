package org.suju.autoupdate.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.suju.autoupdate.R;
import org.suju.autoupdate.entity.MetaModel;
import org.suju.autoupdate.presenter.IUpdatePresenter;
import org.suju.autoupdate.util.ProgressNotificaionUtil;

/**
 * Created by suju on 16/11/1.
 */
public class AutoUpdateDialog extends Dialog implements IUpdateView {

    private TextView mTitleView;
    private TextView mContentView;
    private Button mOkBtn;
    private Button mCancelBtn;
    private IUpdatePresenter mIUpdatePresenter;
    private String mTitle;
    private String mContent;

    public AutoUpdateDialog(Context context) {
        super(context, R.style.autoUpdateDialog);
        setContentView(R.layout.dialog_autoupdate_layout);
        initView();
    }

    public void setPresenter(IUpdatePresenter updatePresenter) {
        this.mIUpdatePresenter = updatePresenter;
    }

    protected void initView() {
        mTitleView = (TextView) findViewById(R.id.tv_autoupdate_title);
        mContentView = (TextView) findViewById(R.id.tv_autoupdate_content);
        mOkBtn = (Button) findViewById(R.id.tv_autoupdate_ok);
        mCancelBtn = (Button) findViewById(R.id.tv_autoupdate_cancel);
        initData();
        initListener();
    }

    private void initListener() {
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIUpdatePresenter.onCancel();
            }
        });
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIUpdatePresenter.onConfirm();
            }
        });
    }

    private void initData() {
        mTitleView.setText(mTitle);
        mContentView.setText(mContent);
    }

    public void setContent(String title) {
        mContentView.setText(title);
    }

    @Override
    public void setViewData(MetaModel metaModel) {
        mTitleView.setText(metaModel.getTitle());
        mContentView.setText(metaModel.getContent());
    }

    @Override
    public void hideView() {
        dismiss();
    }

    @Override
    public void showUpdateView(String downloadUrl) {
        ProgressNotificaionUtil.showNotification(getContext(), downloadUrl);
    }

    @Override
    public void showView() {
        show();
    }
}
