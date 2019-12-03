package com.chatRobot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class HttpConnectionUtil {
	private static Log logger = LogFactory.getLog(HttpConnectionUtil.class);
	public static String put(String urlPath , String Json , Map<String,String> header) {
		 // HttpClient 6.0被抛弃了
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            
            if(header!=null) {
            	for(Map.Entry<String, String> m: header.entrySet()) {
            		 conn.setRequestProperty(m.getKey(),m.getValue());
            	}
            }
            
            // 往服务器里面发送数据
            if (Json != null ) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                logger.debug("doJsonPost: conn"+conn.getResponseCode());
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                //result = reader.readLine();
                
                String line;
                while((line=reader.readLine())!=null) {
                	if(result.length()>0) {
                		result.append("\n");
                	}
                	result.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
	}
	
	
	public static String  post(String urlPath , String Json , Map<String,String> header) {

	        // HttpClient 6.0被抛弃了
	        StringBuilder result = new StringBuilder();
	        BufferedReader reader = null;
	        try {
	            URL url = new URL(urlPath);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            conn.setRequestProperty("Charset", "UTF-8");
	            // 设置文件类型:
	            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
	            // 设置接收类型否则返回415错误
	            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
	            conn.setRequestProperty("accept","application/json");
	            
	            if(header!=null) {
	            	for(Map.Entry<String, String> m: header.entrySet()) {
	            		 conn.setRequestProperty(m.getKey(),m.getValue());
	            	}
	            }
	            
	            // 往服务器里面发送数据
	            if (Json != null) {
	                byte[] writebytes = Json.getBytes();
	                // 设置文件长度
	                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
	                OutputStream outwritestream = conn.getOutputStream();
	                outwritestream.write(Json.getBytes());
	                outwritestream.flush();
	                outwritestream.close();
	                logger.debug("doJsonPost: conn"+conn.getResponseCode());
	            }
	            if (conn.getResponseCode() == 200) {
	                reader = new BufferedReader(
	                        new InputStreamReader(conn.getInputStream()));
	                //result = reader.readLine();
	                
	                String line;
	                while((line=reader.readLine())!=null) {
	                	if(result.length()>0) {
	                		result.append("\n");
	                	}
	                	result.append(line);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return result.toString();
	}
	
	
	
	public static String get(String urlPath , Map<String,String> header) {

        // HttpClient 6.0被抛弃了
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
//            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            
            if(header!=null) {
            	for(Map.Entry<String, String> m: header.entrySet()) {
            		 conn.setRequestProperty(m.getKey(),m.getValue());
            	}
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while((line=reader.readLine())!=null) {
                	if(result.length()>0) {
                		result.append("\n");
                	}
                	result.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
	}
	
	public static String post(String urlPath , String Json) {
		Map<String,String> aa=new HashMap<String,String>();
		aa.put("x-client-id", "APP");
		return post(urlPath,Json,aa);
	}
	
	public static String get(String urlPath) {
		Map<String,String> aa=new HashMap<String,String>();
		aa.put("x-client-id", "APP");
		return get(urlPath,aa);
	}


}
