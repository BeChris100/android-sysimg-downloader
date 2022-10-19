package com.sysimg.downloader;

import com.sysimg.downloader.update.UpdateManager;

import java.io.IOException;

public class MainClass {

    public static void main(String[] args) {
        if (args.length != 0) {
            switch (args[0]) {
                case "--updates", "-u" -> {
                    try {
                        System.out.println("Connecting to \"https://api.github.com/repos/BeChris100/android-sysimg-downloader");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
