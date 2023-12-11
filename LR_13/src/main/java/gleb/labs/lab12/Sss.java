package main.java.gleb.labs.lab12;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/Sss_LR_13")
public class Sss extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet:SSS");
            String filename = req.getParameter("file");
        System.out.println(filename);
            String docdir = getServletContext().getInitParameter("doc-dir");

            OutputDoc(new File(docdir + "\\" + filename), resp);

    }

    protected void OutputDoc(File doc, HttpServletResponse rs)
            throws IOException {
        rs.setContentType("application/msword");    //1
        rs.addHeader("Content-Disposition", "attachment; filename=" +
                doc.getName()); //2
        rs.setContentLength((int) doc.length());    //3

        FileInputStream in = new FileInputStream(doc);
        BufferedInputStream buf = new BufferedInputStream(in);
        ServletOutputStream out = rs.getOutputStream();
        int readBytes = 0;
        while ((readBytes = buf.read()) != -1)
            System.out.println(readBytes);
        out.write(readBytes);

    }
}
