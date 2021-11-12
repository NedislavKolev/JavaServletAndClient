package Servlet;





import MaNik.File.FileRead;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;



//TODO: Raboti PUT Request prez klienta
// GET NE raboti prez klienta i browser (HTTP Error 500)
//


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

//        //specify the file name
//        File file = new File("D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
//
//        //read the content of the file
//        try (BufferedReader br = new BufferedReader(new FileReader(file)))
//        {
//            String line;
//            while ((line = br.readLine()) != null) {
//                //show the content on browser(this is a server response to the client)
//                PrintWriter out = response.getWriter();
//                out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        FileRead fileRead = new FileRead();
        fileRead.readFile("D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
        PrintWriter out = response.getWriter();
        out.println(out);

    }


    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.addHeader("Allow","PUT");

        InputStream bodyStream = request.getInputStream();


        //reading all remaining byte from the input stream
        byte []b = bodyStream.readAllBytes();
        String toWrite = new String(b);

//        try {
//            //Save the incoming data to a file
//            FileWriter putWriter = new FileWriter("D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
//            putWriter.write(toWrite);
//
//            //close the connection
//            putWriter.close();
//            System.out.println("Received file from client: \n" + toWrite);
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }


        FileRead fileRead = new FileRead();
        fileRead.writeToFile(toWrite, "D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\servletfile.xml");
        System.out.println(toWrite);
    }

    public void destroy() {
    }

}