package com.sysimg.downloader.download;

import com.sysimg.downloader.update.UpdateData;
import com.sysimg.downloader.update.UpdateManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadClient {

    public static void checkUpdates() {
        try {
            System.out.println("Connecting to \"https://api.github.com/repos/BeChris100/android-sysimg-downloader");

            List<UpdateData> updateDataList = UpdateManager.listUpdates("https://api.github.com/repos/BeChris100/android-sysimg-downloader/releases");
            if (updateDataList.size() == 0) {
                System.out.println("No releases yet");
                return;
            }

            if (UpdateManager.hasUpdate(updateDataList))
                System.out.println("A newest version of \"Android SysImg Downloader\" is available!");

            int availableChoices = updateDataList.size();

            for (int i = 0; i < availableChoices; i++) {
                UpdateData updateData = updateDataList.get(i);
                System.out.println("Version Tag: " + updateData.tag());
                System.out.println("Release Body: " + updateData.body());
                System.out.println("Files");

                if (i != availableChoices - 1)
                    System.out.println();
            }
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            if (sw.toString().contains("UnknownHostException")) {
                System.err.println("Uh oh, something went fishy with the connection.");
                System.err.println("It may be either on the side of the server, but the first thing you might want to do is to check your Internet Connection.");
                System.err.println();
                System.err.println("Additional Note: Never leak your IP Address, otherwise your Connection will be dead because of the hacker (D)DoS'ing ((Distributed) Denial of Service) you.");
            }
        }
    }

    public static void download(String urlLink, File fileOutput) throws IOException {
    }

    public static String readImagesJsonFile() throws IOException {
        final String CONTENTS_URL = "https://raw.githubusercontent.com/BeChris100/android-sysimg-downloader/master/extras/images.json";

        URL url = new URL(CONTENTS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder jsonBody = new StringBuilder();
        InputStream is = conn.getInputStream();

        byte[] buff = new byte[4096];
        int charData;
        while ((charData = is.read(buff, 0, 4096)) != -1)
            jsonBody.append((char) charData);

        return jsonBody.toString();
    }

}
