package com.sysimg.downloader.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionCheck {

    public static boolean hasConnection() {
        // Picks a random URL Link from here and tries to connect
        String[] URL_LINKS = {
                "https://api.github.com",
                "https://www.google.com",
                "https://www.yahoo.com",
                "https://www.amazon.com",
                "https://www.youtube.com",
                "https://www.github.com",
                "https://www.xbox.com",
                "https://www.microsoft.com",
                "https://www.twitter.com"
        };

        int currentLink = 0;

        try {
            URL url = new URL(URL_LINKS[currentLink]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);

            return true;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            e.printStackTrace(pw);

            if (sw.toString().contains("UnknownHostException")) {
                currentLink++;
                return hasConnection(currentLink);
            } else
                e.printStackTrace();
        }

        return true;
    }

    private static boolean hasConnection(int currentLink) {
        // If the first link fails (https://api.github.com), it will try the other links until it won't reach any links.
        String[] URL_LINKS = {
                "https://api.github.com", // Initial Try
                "https://www.google.com",
                "https://www.yahoo.com",
                "https://www.amazon.com",
                "https://www.youtube.com",
                "https://www.github.com",
                "https://www.xbox.com",
                "https://www.microsoft.com",
                "https://www.twitter.com"
        };

        int link = currentLink;

        try {
            URL url = new URL(URL_LINKS[link]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            return true;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            e.printStackTrace(pw);

            if (sw.toString().contains("UnknownHostException")) {
                link++;

                if (link == URL_LINKS.length)
                    return false;

                return hasConnection(link);
            } else {
                e.printStackTrace();
                return false;
            }
        }
    }

}
