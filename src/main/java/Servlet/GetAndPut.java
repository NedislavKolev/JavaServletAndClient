package Servlet;

import MaNik.File.MNFileLib;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;



//TODO: Raboti PUT Request prez klienta
// GET NE raboti prez klienta i browser (HTTP Error 500)


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
        response.addHeader("Allow","GET");
        response.setContentType("application/xml");

        MNFileLib fileRead = new MNFileLib();
        String toPrint = fileRead.readFile("D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
        PrintWriter out = response.getWriter();
        out.println(toPrint);
        out.flush();

    }


    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.addHeader("Allow","PUT");

        InputStream bodyStream = request.getInputStream();


        //reading all remaining byte from the input stream
        byte []b = bodyStream.readAllBytes();
        String toWrite = new String(b);

        MNFileLib fileRead = new MNFileLib();
        fileRead.writeToFile(toWrite, "D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
        System.out.println(toWrite);
    }

    public void destroy() {
    }

}