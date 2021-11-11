package Servlet;



import MaNik.File.*;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/servlet")
public class GetAndPut extends HttpServlet {


    public void init() {}

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_method = req.getMethod().trim();

        System.out.println(req_method);

        if ("PUT".equals(req_method)){
            doPut(req,resp);
        }
        else if ("GET".equals(req_method)){
            doGet(req,resp);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //set the content type
        response.setContentType("application/xml");

        FileRead fileRead = new FileRead();
        PrintWriter out = response.getWriter();
        out.println(fileRead);

    }


    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.addHeader("Allow","PUT");

        InputStream bodyStream = request.getInputStream();


        //reading all remaining byte from the input stream
        byte []b = bodyStream.readAllBytes();
        String toWrite = new String(b);

        FileRead fileRead = new FileRead();
        fileRead.writeToFile(toWrite, "D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
    }

    public void destroy() {
    }

}