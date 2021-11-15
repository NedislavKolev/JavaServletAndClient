package Servlet;

import MaNik.File.MNFileLib;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//URL Pattern for the servlet
@WebServlet(name = "helloServlet", value = "/servlet")
public class GetAndPut extends HttpServlet {


    //Initializing the servlet
    public void init() {}

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_method = req.getMethod().trim();

        System.out.println(req_method);
        //Checking the request method
        if ("PUT".equals(req_method)){
            doPut(req,resp);
        }
        else if ("GET".equals(req_method)){
            doGet(req,resp);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Added Allow header for GET Request
        response.addHeader("Allow","GET");
        //set the content type
        response.setContentType("application/xml");

        //Using the MaNik external library to read the file
        MNFileLib fileRead = new MNFileLib();
        //Using readFile method do read the file
        String toPrint = fileRead.readFile("D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
        //Printing the read file
        PrintWriter out = response.getWriter();
        out.println(toPrint);
        out.flush();

    }


    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Added Allow header for PUT Request
        response.addHeader("Allow","PUT");

        //Getting the input stream
        InputStream bodyStream = request.getInputStream();
        //Reading all remaining byte from the input stream
        byte []b = bodyStream.readAllBytes();
        String toWrite = new String(b);

        //Using the MaNik external library to write the file
        MNFileLib fileWrite = new MNFileLib();
        //Using writeToFile method do read the file
        fileWrite.writeToFile(toWrite, "D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
        //Printing the written file
        System.out.println(toWrite);
    }

    public void destroy() {
    }

}