package com.junge.aligenie.requestwapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class HttpHelper {
    public static String getBodyString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(
                    new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            char[] bodyCharBuffer = new char[1024];
            int len = 0;
            while ((len = reader.read(bodyCharBuffer)) != -1) {
                sb.append(new String(bodyCharBuffer, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
