package com.xjjlearning.springframework.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjjlearning.springframework.security.entity.Rest;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ResponseUtil
 *
 **/
public class ResponseUtil {
    private ResponseUtil() {
    }



    public static void responseJsonWriter(HttpServletResponse response, Rest rest) throws IOException {

        if (response.isCommitted()){
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(rest);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
