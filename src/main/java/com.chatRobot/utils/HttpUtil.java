package com.chatRobot.utils;

public class HttpUtil {
    public static String getHttpResponseContext(int code, String conetent, String errorMsg) {
        if (code == 200) {
            return "HTTP/1.1 200 OK \n" +
                    "Content-Type: text/html\n" +
                    "\r\n" + conetent;
        } else if (code == 500) {
            return "HTTP/1.1 500 Internal Error=" + errorMsg + " \n" +
                    "Content-Type: text/html\n" +
                    "\r\n";
        }
        return "HTTP/1.1 404 NOT FOUND \n" +
                "Content-Type: text/html\n" +
                "\r\n" +
                "<h1>404 not found</h1>";

    }
}
