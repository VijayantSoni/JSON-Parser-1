package com.vijayant.com.sampletwo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SampleProgramTwo extends AsyncTask<String,String,String> {

    public static final String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=india%20gate%20new%20delhi%20india";

    Context context;
    ProgressDialog p;

    StoreResponse delegate = null;

    String result[] = new String[4];

    public SampleProgramTwo(Context context) {
        this.context = context;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        p.dismiss();
        delegate.store(result);
        delegate = null;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(URL);

            HttpResponse response = client.execute(request);

            InputStream inputStream = response.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"),8);

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            result[0] = jsonObject.getJSONArray("results").getJSONObject(0).getString("formatted_address");
            result[1] = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat");
            result[2] = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lng");
            result[3] = jsonObject.getJSONArray("results").getJSONObject(0).getString("place_id");


            request = null;
            client = null;
            jsonObject = null;
            response = null;
            stringBuilder = null;

            return "true";


        } catch (Exception e) {
            return e.getMessage();

        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        p = new ProgressDialog(context);
        p.setMessage("Please Wait...");
        p.setCancelable(false);
        p.show();
    }
}
