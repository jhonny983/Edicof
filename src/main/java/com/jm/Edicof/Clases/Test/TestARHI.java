/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Johnnatan
 */
public class TestARHI {
    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        TestARHI obj = new TestARHI();

        try {
            System.out.println("Testing 1 - Send Http GET request");
            obj.sendGet();

            System.out.println("Testing 2 - Send Http POST request");
            obj.sendPost();
            
        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private JsonArray sendGet() throws Exception {
        
        JsonArray jsonArray=null;
        HttpGet request = new HttpGet("http://190.84.50.115:8080/awebservicearhi/webresources/entidad.wsrechumano");
        // add request headers
        request.addHeader("AUTHORIZATION", "F50B2000C84C66EA1707502AFB8B8A493D7EB5CA0B4DAB1045310141B3F3F5A1");
        //request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            System.out.println("Status: "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
//            Header headers = entity.getContentType();
//            System.out.println("Headers: "+headers);
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    JsonParser jsonParser = new JsonParser();
                    jsonArray = jsonParser.parse(result).getAsJsonArray();
                    System.out.println("Result: "+jsonArray);
                    for (int i = 0; i < jsonArray.size(); i++){
                        System.out.println("json"+i+": "+jsonArray.get(i));
                    }
                    
                }
            }
            

        }
        return jsonArray;
    }

    private void sendPost() throws Exception {

        HttpPost post = new HttpPost("https://httpbin.org/post");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", "abc"));
        urlParameters.add(new BasicNameValuePair("password", "123"));
        urlParameters.add(new BasicNameValuePair("custom", "secret"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }
}
