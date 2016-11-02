package org.suju.autoupdate.listener;

import org.suju.autoupdate.entity.MetaModel;

/**
 * Created by suju on 16/11/2.
 */
public interface IMetaModelParse {

    MetaModel parse(String data);
}
