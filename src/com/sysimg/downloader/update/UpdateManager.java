package com.sysimg.downloader.update;

import com.sysimg.downloader.client.Build;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UpdateManager {

    private static @NotNull List<UpdateFile> getFiles(@NotNull JSONArray filesRoot) {
        List<UpdateFile> updateFiles = new ArrayList<>();

        for (int i = 0; i < filesRoot.length(); i++) {
            String name = filesRoot.getJSONObject(i).getString("name");
            String urlDownload = filesRoot.getJSONObject(i).getString("browser_download_url");
            long size = filesRoot.getJSONObject(i).getLong("size");

            UpdateFile updateFile = new UpdateFile(name, urlDownload, size, name.contains("bin"));

            if (updateFiles.contains(updateFile))
                continue;

            updateFiles.add(updateFile);
        }

        return updateFiles;
    }

    public static @NotNull List<UpdateData> listUpdates(String urlLink) throws IOException {
        URL url = new URL(urlLink);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder jsonBody = new StringBuilder();
        InputStream is = conn.getInputStream();
        int charData;

        while ((charData = is.read()) != -1)
            jsonBody.append((char) charData);

        List<UpdateData> updatesList = new ArrayList<>();

        JSONArray root = new JSONArray(jsonBody.toString());
        for (int i = 0; i < root.length(); i++) {
            List<UpdateFile> updateFiles = getFiles(root.getJSONObject(i).getJSONArray("assets"));

            updatesList.add(new UpdateData(root.getJSONObject(i).getString("tag_name"),
                    root.getJSONObject(i).getBoolean("prerelease"), updateFiles, root.getJSONObject(i).getString("body")));
        }

        return updatesList;
    }

    public static boolean hasUpdate(@NotNull List<UpdateData> updatesList) {
        String[] splits = Build.BUILD_VERSION.split("\\.");
        int current0 = Integer.parseInt(splits[0]);
        int current1 = Integer.parseInt(splits[1]);
        int current2 = Integer.parseInt(splits[2]);

        boolean retrievesUpdate = false;

        for (UpdateData data : updatesList) {
            String tag = data.tag();
            String[] updateVersion = tag.replaceFirst("v", "").split("\\.");

            if (updateVersion[2].contains("-")) // This can happen, when a pre-release / debug version rolls out
                updateVersion[2] = updateVersion[2].split("-", 2)[0];

            int split0 = Integer.parseInt(updateVersion[0]);
            int split1 = Integer.parseInt(updateVersion[1]);
            int split2 = Integer.parseInt(updateVersion[2]);

            if (split0 > current0 || split1 > current1 || split2 > current2) {
                retrievesUpdate = true;
                break;
            }
        }

        return retrievesUpdate;
    }

}
