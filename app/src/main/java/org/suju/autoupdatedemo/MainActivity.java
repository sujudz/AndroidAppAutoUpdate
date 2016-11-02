package org.suju.autoupdatedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.suju.autoupdate.AutoUpdateFactory;
import org.suju.autoupdate.Config;
import org.suju.autoupdate.entity.MetaModel;
import org.suju.autoupdate.listener.AbstractOnClickListener;
import org.suju.autoupdate.listener.IMetaModelParse;
import org.suju.autoupdate.listener.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IMetaModelParse metaModelParse = new IMetaModelParse() {
            @Override
            public MetaModel parse(String data) {
                return new CustomMeta();
            }
        };

        Config config = new Config.Builder()
                .addContext(this)
                .addMetaData("data")
                .addUpdateUrl("http://demo.edusoho.com/systeminfo")
                .addIMetaModelParse(metaModelParse)
                .builder();
        AutoUpdateFactory.getInstance().getPresenter(config).start();
    }

    class CustomMeta implements MetaModel {

        @Override
        public int getVersionCode() {
            return 0;
        }

        @Override
        public String getContent() {
            return "有新版本发布,是否立即升级?";
        }

        @Override
        public String getTitle() {
            return "升级提醒";
        }

        @Override
        public String getVersionName() {
            return null;
        }

        @Override
        public String getDownloadUrl() {
            return "http://download.edusoho.com/edusohov3-android-3.6.2.apk";
        }
    }
}
