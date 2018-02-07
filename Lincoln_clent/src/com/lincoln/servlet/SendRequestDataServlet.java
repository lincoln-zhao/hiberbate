package com.lincoln.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendRequestDataServlet
 */
@WebServlet("/SendRequestDataServlet")
public class SendRequestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SendRequestDataServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 存放响应结果  
        String resultString = "";
		HttpURLConnection httpConn = null;
		String href = request.getParameter("url");
		String param = request.getParameter("param");
		System.out.println("href:" + href);
		
		try {
			
			ResourceBundle resource = ResourceBundle.getBundle("conf/app");
			String key = resource.getString("server_home");
			key += href;
			System.out.println(key);
			if (param != null && !"".equals(param)) {
				key = key + "?" + param;
			}
			System.out.println(key);
			
			URL url = new URL(key);
			httpConn = (HttpURLConnection) url.openConnection();
			
			httpConn.setRequestMethod("POST");
			
			
			// 读取响应数据  
            int code = httpConn.getResponseCode();
            
            if (code == 200) {
            	String sCurrentLine = "";
            	// 读取响应数据
                InputStream is = httpConn.getInputStream();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                while ((sCurrentLine = reader.readLine()) != null) {
                    if (sCurrentLine.length() > 0) {
                    	resultString = resultString + sCurrentLine.trim();
                    }
                }
                is.close();
                
                System.out.println(resultString);
            } else {
            	System.out.println("else error");
            }
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(resultString);
	}

}
