package org.suju.autoupdate;

import android.text.TextUtils;

import org.suju.autoupdate.presenter.IUpdatePresenter;
import org.suju.autoupdate.presenter.ShowDialogUpdatePresenter;
import org.suju.autoupdate.view.AutoUpdateDialog;
import org.suju.autoupdate.view.IUpdateView;

/**
 * Created by suju on 16/11/1.
 */
public class AutoUpdateFactory {

    public static AutoUpdateFactory getInstance() {
        return SingletonHolder.autoUpdateFactory;
    }

    public IUpdatePresenter getPresenter(Config config) {

        ShowDialogUpdatePresenter updatePresenter = new ShowDialogUpdatePresenter(
                config.getOnClickListener(), config.getMetaModelParse());
        if (!TextUtils.isEmpty(config.getMetaData())) {
            updatePresenter.setMetaData(config.getMetaData());
        } else {
            updatePresenter.setUpdateUrl(config.getUpdateUrl());
        }
        IUpdateView updateView = new AutoUpdateDialog(config.getContext());
        updatePresenter.setView(updateView);
        return updatePresenter;
    }

    private static class SingletonHolder {
        private static final AutoUpdateFactory autoUpdateFactory = new AutoUpdateFactory();
    }
}
