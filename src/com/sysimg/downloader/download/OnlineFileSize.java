package com.sysimg.downloader.download;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class OnlineFileSize {

    public static long getFileSize(String urlLink) throws IOException {
        URL url = new URL(urlLink);
        URLConnection connection = url.openConnection();

        if (connection instanceof HttpsURLConnection)
            ((HttpsURLConnection) connection).setRequestMethod("HEAD");
        else if (connection instanceof HttpURLConnection)
            ((HttpURLConnection) connection).setRequestMethod("HEAD");

        connection.getInputStream();
        return connection.getContentLengthLong();
    }

}
