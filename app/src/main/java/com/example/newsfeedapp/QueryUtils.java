package com.example.newsfeedapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    public static List<newsfeedapp> fetchNewsFeedApp(String requestUrl) {
        URL url = createURL(requestUrl);
        String jsonresponse = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            jsonresponse = makehttpresponse(url);
        }
        catch(IOException e){
            Log.e(LOG_TAG,"Problem making the Http request.",e);
        }
        List<newsfeedapp> newsfeedapps = extractFeatureFromJson(jsonresponse);
        return newsfeedapps;
    }

    private static URL createURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem Building the URL", e);
        }
        return url;
    }

    private static String makehttpresponse(URL url) throws IOException {
        String jsonresponse = "";
        if (url == null) {
            return jsonresponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonresponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code : " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonresponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<newsfeedapp> extractFeatureFromJson(String newsJson) {
        if(TextUtils.isEmpty(newsJson)){
            return null;
        }
        ArrayList<newsfeedapp> newsJsons = new ArrayList<newsfeedapp>();
        try{
            JSONObject baseJsonObject = new JSONObject(newsJson);
            JSONObject firstArrayObject = baseJsonObject.getJSONObject("response");
            JSONArray jsonArray = firstArrayObject.getJSONArray("results");
            for(int i=0; i<jsonArray.length() ;i++){
                JSONObject currentJSON = jsonArray.getJSONObject(i);
                String webpublisheddate =currentJSON.getString("webPublicationDate");
                String webUrl =currentJSON.getString("webUrl");
                String webTitle =currentJSON.getString("webTitle");
                newsfeedapp Newsfeedapp = new newsfeedapp(webTitle,webpublisheddate,webUrl);
                newsJsons.add(Newsfeedapp);
            }
        }
        catch(JSONException e)
        {
           Log.e("QueryUtils","Problem parsing the newsJson results",e);
        }
        return newsJsons;
    }


}

