package com.sysimg.downloader.settings;

import com.sysimg.downloader.utils.FileUtil;
import com.sysimg.downloader.utils.RuntimeEnvironment;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SettingsManager {

    private JSONObject objectRoot;

    public SettingsManager(@NotNull String settingsFile) throws IOException {
        boolean hasJsonEnding = settingsFile.endsWith(".json");

        String filePath;

        if (RuntimeEnvironment.isMac())
            filePath = RuntimeEnvironment.USER_HOME.getPath() + "/Library/Application Support/Android SysImg Downloader/configs/";
        else if (RuntimeEnvironment.isLinux())
            filePath = RuntimeEnvironment.USER_HOME.getPath() + "/.config/Android SysImg Downloader/configs/";
        else if (RuntimeEnvironment.isWindows())
            filePath = RuntimeEnvironment.USER_HOME.getPath() + "\\AppData\\Local\\Android SysImg Downloader\\configs\\";
        else
            throw new RuntimeException("Could not determine an Operating System");

        if (hasJsonEnding)
            filePath = settingsFile;
        else
            filePath = settingsFile + ".json";

        String jsonBody = new String(FileUtil.read(filePath));
        objectRoot = new JSONObject(jsonBody);
    }

    protected SettingsManager(JSONObject objectRoot) {
        if (objectRoot != null)
            this.objectRoot = objectRoot;
    }

    public int getInteger(String key) {
        return objectRoot.getInt(key);
    }

    public String getString(String key) {
        return objectRoot.getString(key);
    }

    public long getLong(String key) {
        return objectRoot.getLong(key);
    }

    public float getFloat(String key) {
        return objectRoot.getFloat(key);
    }

    public BigInteger getBigInteger(String key) {
        return objectRoot.getBigInteger(key);
    }

    public BigDecimal getBigDecimal(String key) {
        return objectRoot.getBigDecimal(key);
    }

    public boolean getBoolean(String key) {
        return objectRoot.getBoolean(key);
    }

    public double getDouble(String key) {
        return objectRoot.getDouble(key);
    }

    public SettingsList getList(String key) {
        return new SettingsList(objectRoot.getJSONArray(key));
    }

    public SettingsManager getSettingsManagerFromRoot(String root) {
        return new SettingsManager(objectRoot.getJSONObject(root));
    }

    public void setInteger(String key, int value) {
        objectRoot.put(key, value);
    }

    public void setString(String key, String value) {
        objectRoot.put(key, value);
    }

    public void setLong(String key) {
        objectRoot.getLong(key);
    }

    public void setFloat(String key, float value) {
        objectRoot.put(key, value);
    }

    public void setBigInteger(String key, BigInteger value) {
        objectRoot.put(key, value);
    }

    public void setBigDecimal(String key, BigDecimal value) {
        objectRoot.put(key, value);
    }

    public void setBoolean(String key, boolean value) {
        objectRoot.put(key, value);
    }

    public void setDouble(String key, double value) {
        objectRoot.put(key, value);
    }

    public void setList(String key, @NotNull SettingsList value) {
        objectRoot.put(key, value.getArray());
    }

    public void setData(String key, @NotNull SettingsManager data) {
        objectRoot.put(key, data.getRootObject());
    }

    protected JSONObject getRootObject() {
        return objectRoot;
    }

}
