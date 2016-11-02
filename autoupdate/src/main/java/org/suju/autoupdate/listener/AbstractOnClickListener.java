package org.suju.autoupdate.listener;

/**
 * Created by suju on 16/11/2.
 */
public class AbstractOnClickListener implements OnClickListener {

    @Override
    public boolean onCancel() {
        return true;
    }

    @Override
    public boolean onConfirm() {
        return false;
    }
}
