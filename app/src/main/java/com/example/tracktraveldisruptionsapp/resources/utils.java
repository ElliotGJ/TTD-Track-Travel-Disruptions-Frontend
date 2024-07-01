package com.example.tracktraveldisruptionsapp.resources;

import java.io.*;
import java.nio.file.Files;


public class utils {

    public static String fileToString(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String content = "";

            while (content != null) {
                content = br.readLine();
                sb.append(content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    return sb.toString();
    }

}
