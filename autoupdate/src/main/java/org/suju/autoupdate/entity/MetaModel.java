package org.suju.autoupdate.entity;

/**
 * Created by suju on 16/11/2.
 */
public interface MetaModel {

    int getVersionCode();

    String getVersionName();

    String getTitle();

    String getContent();

    String getDownloadUrl();
}
