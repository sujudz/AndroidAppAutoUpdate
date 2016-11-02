package org.suju.autoupdate.presenter;

/**
 * Created by suju on 16/11/2.
 */
public interface IUpdatePresenter {

    void start();

    void onCancel();

    void onConfirm();
}
