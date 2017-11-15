package com.defaulty.autopark.files;

import java.io.IOException;

public interface FileCRUD {

    String get(String fileName) throws IOException;

    void put(String fileName, String data) throws IOException;

    void write(String fileName, String data) throws IOException;

    void append(String fileName, String data) throws IOException;
}
