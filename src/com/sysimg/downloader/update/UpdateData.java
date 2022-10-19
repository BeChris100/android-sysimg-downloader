package com.sysimg.downloader.update;

import java.util.List;

public record UpdateData(String tag, boolean preRelease, List<UpdateFile> updateFiles, String body) {
}
