package com.sysimg.downloader.settings;

import com.sysimg.downloader.utils.FileUtil;
import com.sysimg.downloader.utils.RuntimeEnvironment;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SettingsList {

    private JSONArray array;

    public SettingsList(@NotNull String settingsFile) throws IOException {
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
        array = new JSONArray(jsonBody);
    }

    protected SettingsList(JSONArray array) {
        if (array != null)
            this.array = array;
    }

    protected JSONArray getArray() {
        return array;
    }

    public int getInteger(int index) {
        return array.getInt(index);
    }

    public String getString(int index) {
        return array.getString(index);
    }

    public long getLong(int index) {
        return array.getLong(index);
    }

    public float getFloat(int index) {
        return array.getFloat(index);
    }

    public BigInteger getBigInteger(int index) {
        return array.getBigInteger(index);
    }

    public BigDecimal getBigDecimal(int index) {
        return array.getBigDecimal(index);
    }

    public boolean getBoolean(int index) {
        return array.getBoolean(index);
    }

    public double getDouble(int index) {
        return array.getDouble(index);
    }

    public SettingsList getList(int index) {
        return new SettingsList(array.getJSONArray(index));
    }

    public SettingsManager getSettingsManagerFromRoot(int index) {
        return new SettingsManager(array.getJSONObject(index));
    }

    public void setInteger(int index, int value) {
        array.put(index, value);
    }

    public void setInteger(int value) {
        array.put(value);
    }

    public void setString(int index, String value) {
        array.put(index, value);
    }

    public void setString(String value) {
        array.put(value);
    }

    public void setLong(int index, long value) {
        array.put(index, value);
    }

    public void setLong(long value) {
        array.put(value);
    }

    public void setFloat(int index, float value) {
        array.put(index, value);
    }

    public void setBigInteger(int index, BigInteger value) {
        array.put(index, value);
    }

    public void setBigInteger(BigInteger value) {
        array.put(value);
    }

    public void setBigDecimal(int index, BigDecimal value) {
        array.put(index, value);
    }

    public void setBigDecimal(BigDecimal value) {
        array.put(value);
    }

    public void setBoolean(int index, boolean value) {
        array.put(index, value);
    }

    public void setBoolean(boolean value) {
        array.put(value);
    }

    public void setDouble(int index, double value) {
        array.put(index, value);
    }

    public void setDouble(double value) {
        array.put(value);
    }

    public void setList(int index, @NotNull SettingsList value) {
        array.put(index, value);
    }

    public void setList(@NotNull SettingsList value) {
        array.put(value);
    }

    public void setData(int index, @NotNull SettingsManager data) {
        array.put(index, data.getRootObject());
    }

    public void setData(@NotNull SettingsManager data) {
        array.put(data.getRootObject());
    }

}
