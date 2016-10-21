package widget;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.*;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.google.gson.Gson;




/**
 * Created by jsnow on 10/19/2016.
 */
public class weather {



    public static String weather_pic() throws IOException {

        // Connect to the URL using java's native library
        // Please get a api key from api.underground.com 
        String sURL = "http://api.wunderground.com/api/<YOUR KEY>/conditions/q/<STATE>/<CITY>.json"; //just a string
        URL url = null;
        try {
            url = new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        JsonObject cond = rootobj.get("current_observation").getAsJsonObject();

        String icon = cond.get("icon_url").getAsString();

        System.out.println(icon);
        //String zipcode = rootobj.get("query").getAsString(); //just grab the zipcode

        return icon;
    }


    public static String temp() {

        // Please get a api key from api.underground.com 
        String sURL = "http://api.wunderground.com/api/<YOUR KEY>/conditions/q/<STATE>/<CITY>.json"; //just a string
        URL url = null;
        try {
            url = new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        JsonObject cond = rootobj.get("current_observation").getAsJsonObject();

        String temp_f = cond.get("temp_f").toString();

        BigDecimal x_t = new BigDecimal(temp_f);

        BigDecimal con_f =  x_t.setScale(0, BigDecimal.ROUND_HALF_UP);

        String temp_f_r = con_f.toString();

        System.out.println(temp_f_r);
        //String zipcode = rootobj.get("query").getAsString(); //just grab the zipcode
        String temp  = temp_f_r + "°F";
        return temp;
    }

    public static String sunup() {

        // Connect to the URL using java's native library.
   	// Please get a api key from api.underground.com 
        
        String sURL = "http://api.wunderground.com/api/<YOUR KEY>/astronomy/q/<STATE>/<CITY>.json"; //just a string
        URL url = null;
        try {
            url = new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        JsonObject ast = rootobj.get("sun_phase").getAsJsonObject();


        JsonObject sun = ast.get("sunrise").getAsJsonObject();

        String sun_h = sun.get("hour").getAsString();

        String sun_m = sun.get("minute").getAsString();

        String sun_u_h_m = "☼" + " " + sun_h + ":" + sun_m + " " + "AM";

        System.out.println(sun_u_h_m);

        String sunup  = sun_u_h_m;
        return sunup;
    }

    public static String sundown() {

        // Connect to the URL using java's native library
   	// Please get a api key from api.underground.com 
        
        String sURL = "http://api.wunderground.com/api/<YOUR KEY>/astronomy/q/<STATE>/<CITY>.json"; //just a string
        URL url = null;
        try {
            url = new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        JsonObject ast = rootobj.get("sun_phase").getAsJsonObject();


        JsonObject sun = ast.get("sunset").getAsJsonObject();

        String sun_h = sun.get("hour").getAsString();

        String tw_h = null;

        switch (sun_h){
            case "13":
                tw_h = "1";
                break;
            case "14":
                tw_h = "2";
                break;
            case "15":
                tw_h = "3";
                break;
            case "16":
                tw_h = "4";
                break;
            case "17":
                tw_h = "5";
                break;
            case "18":
                tw_h = "6";
                break;
            case "19":
                tw_h = "7";
                break;
            case "20":
                tw_h = "8";
                break;
            case "21":
                tw_h = "9";
                break;
            case "22":
                tw_h = "10";
                break;
            case "23":
                tw_h = "11";
                break;
            case "24":
                tw_h = "12";
                break;
        }

        String sun_m = sun.get("minute").getAsString();

        String sun_d_h_m = "☽" + " "  + tw_h + ":" + sun_m + " " + "PM";

        System.out.println(sun_d_h_m);

        String sund  = sun_d_h_m;
        return sund;
    }




}