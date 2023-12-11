package main.java.gleb.labs.lab12;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.io.File;

public class ChoseFile {
    public String list[];
    protected class Only implements FilenameFilter {
        String xxx = null;
        public Only(String xxx) {
            this.xxx = "." + xxx;
        }
        public boolean accept(File d, String name) {
            return name.endsWith(xxx);
        }
    }
    public ChoseFile(String d, String xxx) {
        File dir = new File(d);
        if (dir.exists()) {
            list = dir.list(new Only(xxx));
        }
    }
}
