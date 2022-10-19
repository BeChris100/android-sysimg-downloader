package com.sysimg.downloader;

import com.sysimg.downloader.download.DownloadClient;

public class MainClass {

    public static void main(String[] args) {
        if (args.length != 0) {
            switch (args[0]) {
                case "--updates", "-u" -> DownloadClient.checkUpdates();
                case "--change", "-c" -> {
                    if (args.length != 2) {
                        String options = "settings";

                        if (args[0].equals("-c")) {
                            System.out.println("Use of -c");
                            System.out.println("-c " + options);
                        } else {
                            System.out.println("Use of --change");
                            System.out.println("--change " + options);
                        }

                        System.exit(1);
                        return;
                    }
                }
            }
        }

        new Thread(() -> {

        });
    }

}
