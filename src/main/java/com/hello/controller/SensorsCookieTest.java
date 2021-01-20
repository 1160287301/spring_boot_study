package com.hello.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class SensorsCookieTest {

    @ResponseBody
    @RequestMapping("/sensors")
    public String Test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 测试账号 test@123.com
        String userName = request.getParameter("user");
        System.out.println("user: " + userName);
        JSONObject postData = new JSONObject();
        String url;
        String apiUrl = "http://10.120.102.101:8107";
        url = apiUrl + "/api/v2/auth/login?project=default&is_global=true";
        postData.put("account_name", "15026549789");
        postData.put("password", "test@15026549789");
        postData.put("expired_interval", "30");
        RestTemplate client = new RestTemplate();
        ResponseEntity<JSONObject> httpResponse = client.postForEntity(url, postData, JSONObject.class);
        System.out.println("StatusCode: " + httpResponse.getStatusCode());
        System.out.println("getBody: " + httpResponse.getBody());
        HttpHeaders headers = httpResponse.getHeaders();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            System.out.println(entry);
            System.out.println("-------------");
            String headerName = entry.getKey();
            if ("Content-Length".equals(headerName)) {
                continue;
            }
            List<String> values = entry.getValue();
            for (String value : values) {
                response.addHeader(headerName, value);
            }
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        return "success";
    }
}
