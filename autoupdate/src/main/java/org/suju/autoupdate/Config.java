package org.suju.autoupdate;

import android.content.Context;

import org.suju.autoupdate.entity.MetaModel;
import org.suju.autoupdate.listener.IMetaModelParse;
import org.suju.autoupdate.listener.OnClickListener;

/**
 * Created by suju on 16/11/2.
 */
public class Config {

    private Context context;
    private String updateUrl;
    private String metaData;
    private OnClickListener onClickListener;
    private IMetaModelParse metaModelParse;

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IMetaModelParse getMetaModelParse() {
        return metaModelParse;
    }

    public void setMetaModelParse(IMetaModelParse metaModelParse) {
        this.metaModelParse = metaModelParse;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private Config() {
    }

    public static class Builder {

        private Config config;

        public Builder() {
            config = new Config();
        }

        public Builder addContext(Context context) {
            config.setContext(context);
            return this;
        }

        public Builder addIMetaModelParse(IMetaModelParse metaModelParse) {
            config.setMetaModelParse(metaModelParse);
            return this;
        }

        public Builder addMetaData(String metaData) {
            config.setMetaData(metaData);
            return this;
        }

        public Builder addUpdateUrl(String updateUrl) {
            config.setUpdateUrl(updateUrl);
            return this;
        }

        public Config builder() {
            return config;
        }
    }
}
