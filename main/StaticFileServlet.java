package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StaticFileServlet extends HttpServlet
{
    private String strNameFile="1";
    
    public StaticFileServlet(String strNameFile)
    {
        this.strNameFile=strNameFile;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
		try (FileInputStream fin = new FileInputStream(strNameFile)){
			byte[] fromF = new byte[fin.available()];
			fin.read(fromF);
			String strF = new String(fromF, Charset.forName("Cp1251"));
	        //response.setContentType("text/css");
	        response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().println(strF);
			
		} catch (Exception e) {
			System.out.println("Видимо ошибка с файлом " + strNameFile);
			System.out.println("ошибка " + e);
		}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doGet(request, response);
    }
}