package com.example.android.booklister;

import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class query {
    private static final String TAG = "well well well";
    public static List<books> apicall(String url){
        URL URl = urlmaker(url);
        String jsonresponse= connect(URl);
        List<books> bookitems= new ArrayList<>();
        bookitems=fetchdata(jsonresponse);
        return bookitems;
    }

    public static URL urlmaker(String url) {

        URL Url = null;


            try {
                Url = new URL(url);
            } catch (Exception e) {
                Log.d(TAG, "urlmaker: Url error found");
            }
            return Url;
        }

    public static String connect(URL url){
        String jres="";
        if(url==null){
            return jres;
        }
        HttpURLConnection connecter=null;
        InputStream inputStream=null;
        try{
            connecter=(HttpURLConnection) url.openConnection();

            connecter.setConnectTimeout(15000);
            connecter.setReadTimeout(10000);
            connecter.setRequestMethod("GET");
            connecter.connect();
            if(connecter.getResponseCode()==200){
                inputStream=connecter.getInputStream();
                jres=getStream(inputStream);
            }else{
                Log.e("error","there was an error in getting the response");
            }

        }catch (Exception e){
            Log.d(TAG, "connect: error found");
        }finally{
            if(connecter!=null)
                connecter.disconnect();
            try{
            if(inputStream!=null)
                inputStream.close();}
            catch (Exception e){
                Log.d(TAG, "connect: inputStream error");
            }
    }return jres;
    }

    private static String getStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            try {
               line =reader.readLine();
               while(line!=null){
                   output.append(line);
                   line=reader.readLine();
               }
            }catch (Exception e) {
                Log.d(TAG, "getStream: error found");
            }
        }return output.toString();
    }
    public static List<books> fetchdata(String jsonres){

        if(TextUtils.isEmpty(jsonres)){
            return null;
        }List<books> booksList= new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonres);
            JSONArray item = root.getJSONArray("items");
            for (int i = 0; i < item.length(); i++) {
                JSONObject itemarray = item.getJSONObject(i);
                JSONObject volinfo = itemarray.getJSONObject("volumeInfo");
                JSONObject imageinfo = volinfo.getJSONObject("imageLinks");
//                JSONArray author = volinfo.getJSONArray("authors");
//                int length = author.length();
//
//                String[] authors = new String[length];
//                for (int j = 0; j < length; j++) {
//                    authors[i] = author.getString(i);
//                }
                books book = new books(volinfo.getString("title"),
                        volinfo.getString("publisher"),
                        imageinfo.getString("thumbnail"));
                booksList.add(book);
            }
        }
        catch (Exception e){
            Log.d(TAG, "fetchdata: fetching error");
        }
        return booksList;
//        authors,
    }
}