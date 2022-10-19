package com.sysimg.downloader.unzip;

import com.sysimg.downloader.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipPackage {

    public static void extractDownloadedPackage(File zipFile, File outputDirectory, ExtractInterface extractInterface) throws IOException {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<? extends ZipEntry> listEntries = zip.entries();

        List<String> entriesList = new ArrayList<>();
        while (listEntries.hasMoreElements())
            entriesList.add(listEntries.nextElement().getName());

        for (String entry : entriesList) {
            ZipEntry zipEntry = zip.getEntry(entry);

            if (zipEntry.isDirectory()) {
                FileUtil.createDirectory(outputDirectory.getPath() + "/" + zipEntry.getName());

                if (extractInterface != null)
                    extractInterface.onEntryExtracted(entry, new File(outputDirectory.getPath() + "/" + zipEntry.getName()), true);

                continue;
            }

            InputStream inStream = zip.getInputStream(zipEntry);

            File outputFile = new File(outputDirectory.getPath() + "/" + zipEntry.getName());
            if (!outputFile.exists())
                FileUtil.createFile(outputFile.getPath(), true);

            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] buff = new byte[8192];
            int data;
            while ((data = inStream.read(buff, 0, 8192)) != -1) {
                fos.write(buff, 0, data);

                if (extractInterface != null)
                    extractInterface.onEntryExtracting(entry, new File(outputFile.getPath()).length(), zipEntry.getSize());
            }

            fos.close();
            inStream.close();

            if (extractInterface != null)
                extractInterface.onEntryExtracted(entry, outputFile, false);
        }
    }

    public interface ExtractInterface {
        void onEntryExtracting(String packageEntry, long writtenData, long entrySize);

        void onEntryExtracted(String packageEntry, File extractedFile, boolean isDirectory);
    }

}
