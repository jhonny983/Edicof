/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 *
 * @author Johnnatan
 */
public class WebServiceARHI {
    String resource_ws_rechumano = "/awebservicearhi/webresources/entidad.wsrechumano";
    String resource_ws_ordentrabajo = "/awebservicearhi/webresources/entidad.wsordentrabajo";
    String host = "";
    String auth = "";
    String user = "";
    public static void main(String[] args) throws Exception {
        WebServiceARHI obj = new WebServiceARHI();
        System.out.println("Testing 1 - Send Http GET request");
        //obj.post_ws_rechumano("","","","","");
        obj.get_ws_rechumano("1");
        
    }
    public WebServiceARHI(){
        this.host=get_parameter("HOST_ARHI");
        this.auth=get_parameter("AUTHORIZATION");
        this.user=get_parameter("USER_ARHI");
    }
    private boolean post_ws_rechumano(String ced, String empresa, String f_ingreso, String f_retiro, String tipo){
        boolean ret=false;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        System.out.println("Objeto: "+get_data_ws_rechumano("458399","900991293","2018-03-08","2018-06-05","2"));
        HttpPost post = new HttpPost(this.host+this.resource_ws_rechumano);
        post.addHeader("AUTHORIZATION", this.auth);
        post.addHeader("content-type", "application/json");
        try{
            //StringEntity params = new StringEntity(get_data_ws_rechumano("458399","900991293","2018-03-08","2018-06-05","2").toString());
            StringEntity params = new StringEntity("{\"RHApellido1\":\"MORALES\",\"RHApellido2\":\"MORALES\",\"RHGenero\":\"M\",\"RHNacionalidad\":\"49\",\"RHNombre1\":\"ISRAEL\",\"RHNombre2\":\"\",\"RHNombre3\":\"\",\"RHTipsangre\":\"7\",\"RHfecNac\":\"1900-01-01T00:00:00-05:00\",\"RHlugNac\":\"76001\",\"wsRechumanoPK\":{\"RHIdentific\":\"3\",\"RHTipIdent\":\"1\"},\"RHdireccion\":\"CALLE 43 # 12-17\",\"RHestCiv\":\"4\",\"RHnumHij\":\"1\",\"RHtelfoCelu\":\"3122353210-3173101728\",\"RHeps\":\"5\",\"RHafp\":\"2\",\"RHantiguedad\":\"2018-03-08T00:00:00-05:00\",\"RHUsuario\":\"973088\"}");            
            post.setEntity(params);
            CloseableHttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode()==204) {
                ret=true;
            }
            //System.out.println(EntityUtils.toString(response.getEntity()));
            httpClient.close();
        }catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return ret;
    }
    private JsonArray get_ws_rechumano(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JsonArray json_response =null;
        HttpGet request = new HttpGet(this.host+this.resource_ws_rechumano);
        request.addHeader("AUTHORIZATION", this.auth);
        try{ 
            
            CloseableHttpResponse response = httpClient.execute(request);
            //System.out.println("Status: "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    JsonParser jsonParser = new JsonParser();
                    json_response = jsonParser.parse(result).getAsJsonArray();
//                    System.out.println("Result: "+json_response);
//                    for (int i = 0; i < json_response.size(); i++){
//                        System.out.println("json"+i+": "+json_response.get(i));
//                    }
                }
            }
            httpClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
            
        }
        return json_response;
    }
    private JsonArray get_ws_rechumano(String ced){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JsonArray json_response =null;
        HttpGet request = new HttpGet(this.host+this.resource_ws_rechumano+"/"+ced);
        request.addHeader("AUTHORIZATION", this.auth);
        try{ 
            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println("Status: "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    JsonParser jsonParser = new JsonParser();
                    json_response = jsonParser.parse(result).getAsJsonArray();
                    System.out.println("Result: "+json_response);
                    for (int i = 0; i < json_response.size(); i++){
                        System.out.println("json"+i+": "+json_response.get(i));
                    }
                }
            }
            httpClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
            
        }
        return json_response;
    }
    ////////*******************************************************///////////////////
    private JsonObject get_data_ws_rechumano (String c, String empresa, String f_ingreso, String f_retiro, String tipo){
        JsonObject jsonObject = new JsonObject();
        
        Conexion con = new Conexion("127.0.0.1","e_occidente","root","jhonny3029735");
        con.conexion();
        ResultSet r;
        try{
            
            r = con.s.executeQuery (  "SELECT * "
                                    + "FROM t_empleados "
                                    + "INNER JOIN `t_genero` \n"
                                    + " ON (`t_empleados`.`ID_TIPO_GENERO` = `t_genero`.`ID_GENERO`)"
                                    + "WHERE ID_EMP = "+c+";");
            if(r.next()){
                jsonObject.addProperty("RHApellido1", r.getString("APELLIDO_1_EMP"));
                jsonObject.addProperty("RHApellido2", r.getString("APELLIDO_1_EMP"));
                jsonObject.addProperty("RHGenero", r.getString("NOMBRE_GENERO"));
                jsonObject.addProperty("RHNacionalidad", r.getString("ID_NACION"));
                jsonObject.addProperty("RHNombre1", r.getString("NOMBRE_1_EMP"));
                jsonObject.addProperty("RHNombre2", r.getString("NOMBRE_2_EMP"));
                jsonObject.addProperty("RHNombre3", "");
                jsonObject.addProperty("RHTipsangre", r.getString("ID_TIPO_SANGRE"));//973088
                jsonObject.addProperty("RHfecNac", getISO8601(r.getString("FECHA_NAC")));
                jsonObject.addProperty("RHlugNac", r.getString("ID_MUN_NACIMIENTO"));
                JsonObject innerPK = new JsonObject();
                innerPK.addProperty("RHIdentific", r.getString("ID_EMP"));
                innerPK.addProperty("RHTipIdent", r.getString("ID_TIPO_IDENT"));
                jsonObject.add("wsRechumanoPK", innerPK );
            }
            r = con.s.executeQuery (  "SELECT * "
                                    + "FROM t_info_sociodemografica "
                                    + "WHERE ID_EMP = "+c+";");
            if (r.next()) {
                jsonObject.addProperty("RHdireccion", r.getString("DIRECCION_EMP"));
                jsonObject.addProperty("RHestCiv", r.getString("ID_ESTADO_CIVIL_EMP"));
                jsonObject.addProperty("RHnumHij", r.getString("NUM_HIJOS_EMP"));
                jsonObject.addProperty("RHtelfoCelu", r.getString("TEL_CEL_EMP"));
            }
            r = con.s.executeQuery (  "SELECT * "
                                    + "FROM t_novedades "
                                    + "WHERE ID_EMPLEADO = "+c+" AND ID_EMPRESA ='"+empresa+"' AND FECHA_INGRESO = '"+f_ingreso+"' AND FECHA_RETIRO = '"+f_retiro+"';");
            if (r.next()) {
                jsonObject.addProperty("RHeps", r.getString("ID_EPS"));
                jsonObject.addProperty("RHafp", r.getString("ID_AFP"));
            }
            r = con.s.executeQuery (  "SELECT * "
                                    + "FROM t_novedades "
                                    + "WHERE ID_EMPLEADO = "+c+" ORDER BY FECHA_INGRESO ASC;");
            if (r.next()) {
                jsonObject.addProperty("RHantiguedad", getISO8601(r.getString("FECHA_INGRESO")));
                
            }
            jsonObject.addProperty("RHUsuario", this.user);
            con.cerrar();
        }catch(SQLException j){
            j.printStackTrace();
            con.cerrar();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return jsonObject;
    }
    ////////*******************************************************///////////////////
    private String get_parameter(String par){
        String ret ="";
        Conexion con = new Conexion("127.0.0.1","e_occidente","root","jhonny3029735");
        con.conexion();
        ResultSet r;
        try{
            r = con.s.executeQuery ("SELECT * FROM t_parametros WHERE NOMBRE_PAR = '"+par+"';");
            if(r.next()){
                ret=r.getString("VALOR_PAR");
            }
            con.cerrar();
        }catch(SQLException j){
            con.cerrar();
            JOptionPane.showMessageDialog(null,j,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return ret;
    }
    private String getISO8601 (String d){//Formato de fecha yyyy-MM-dd
        String date="";
        try {
            SimpleDateFormat f_n =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date=f_n.format(new SimpleDateFormat("yyyy-MM-dd").parse(d))+"-05:00";
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }
}
