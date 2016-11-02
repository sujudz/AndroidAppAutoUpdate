package org.suju.autoupdate.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by suju on 16/11/1.
 */
public class RequestUtil {

    public static String get(String requestUrl) {
        BufferedReader input = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL(requestUrl);
            try {
                // default gzip encode
                con = (HttpURLConnection)url.openConnection();
                input = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = input.readLine()) != null) {
                    sb.append(s).append("\n");
                }

                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } finally {
            // close buffered
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }

        return null;
    }
}
