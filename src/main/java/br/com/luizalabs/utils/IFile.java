package br.com.luizalabs.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface IFile {


    boolean isFile(String path);
    void read(String path);
    void write(Object obj, String path);

}
