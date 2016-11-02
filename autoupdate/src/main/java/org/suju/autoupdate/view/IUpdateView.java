package org.suju.autoupdate.view;

import org.suju.autoupdate.entity.MetaModel;
import org.suju.autoupdate.presenter.IUpdatePresenter;

/**
 * Created by suju on 16/11/2.
 */
public interface IUpdateView {

    void hideView();

    void showView();

    void setViewData(MetaModel metaModel);

    void setPresenter(IUpdatePresenter presenter);

    void showUpdateView(String downloadUrl);
}
