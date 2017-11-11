package com.defaulty.autopark.service.report;

import java.io.*;

public class FileCRUDImpl implements FileCRUD {

    @Override
    public String get(String fileName) throws IOException {
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = new FileInputStream(file);
        int data = fis.read();
        while (data != -1) {
            sb.append((char) data);
            data = fis.read();
        }
        fis.close();

        return sb.toString();
    }

    @Override
    public void put(String fileName, String data) throws IOException {
        output(fileName, data, false);
    }

    @Override
    public void write(String fileName, String data) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(data);
        writer.close();
    }

    @Override
    public void append(String fileName, String data) throws IOException {
        output(fileName, data, true);
    }

    private void output(String fileName, String data, boolean flag) throws IOException {
        try (FileWriter fw = new FileWriter(fileName, flag);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(data);
        }
    }

}
