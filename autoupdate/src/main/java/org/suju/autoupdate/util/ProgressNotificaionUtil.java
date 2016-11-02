package org.suju.autoupdate.util;

import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

/**
 * Created by suju on 16/11/3.
 */
public class ProgressNotificaionUtil {

    public static void showNotification(Context context, String downloadUrl) {
        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager;
        downloadManager = (DownloadManager) context.getSystemService(serviceString);

        Uri uri = Uri.parse(downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");

        String name = TextUtils.isEmpty(uri.getLastPathSegment()) ? "update.apk" : uri.getLastPathSegment();
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, name);
        long reference = downloadManager.enqueue(request);
    }
}
