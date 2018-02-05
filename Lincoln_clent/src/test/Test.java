package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Test {

	public static void main(String[] args) {
		// 存放响应结果  
        String sTotalString = "";
		HttpURLConnection httpConn = null;
		OutputStream os = null;
        OutputStreamWriter osw = null;
		try {
			
			ResourceBundle resource = ResourceBundle.getBundle("conf/app");
			String key = resource.getString("server_home");
			key += "/user/getAllUsers";
			System.out.println(key);
			URL url = new URL(key);
			httpConn = (HttpURLConnection) url.openConnection();
			
			httpConn.setRequestMethod("POST");
			
			httpConn.connect(); 
			
//			os = httpConn.getOutputStream();  
//            osw = new OutputStreamWriter(os);  
//            osw.write(json.toCharArray(), 0, json.length());  
//            osw.flush();
			
			// 读取响应数据  
            int code = httpConn.getResponseCode();
            
            if (code == 200) {
            	String sCurrentLine = ""; 
            	// 读取响应数据  
                InputStream is = httpConn.getInputStream();
                
                BufferedReader reader = new BufferedReader(  
                        new InputStreamReader(is));  
                while ((sCurrentLine = reader.readLine()) != null) {  
                    if (sCurrentLine.length() > 0) {  
                        sTotalString = sTotalString + sCurrentLine.trim();  
                    }  
                }  
                is.close();  
                System.out.println(sTotalString);  
            } else {
            	System.out.println("else error");
            }
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
