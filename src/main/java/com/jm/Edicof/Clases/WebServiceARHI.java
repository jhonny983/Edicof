/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
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
public class WebServiceARHI {
    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

//    public static void main(String[] args) throws Exception {
//
//        WebServiceARHI obj = new WebServiceARHI();
//
//        try {
//            System.out.println("Testing 1 - Send Http GET request");
//            obj.sendGet();
//
//            System.out.println("Testing 2 - Send Http POST request");
//            obj.sendPost();
//        } finally {
//            obj.close();
//        }
//    }

    private void close(){
        try {
            httpClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);
        request.addHeader("AUTHORIZATION", "F50B2000C84C66EA1707502AFB8B8A493D7EB5CA0B4DAB1045310141B3F3F5A1");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

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
