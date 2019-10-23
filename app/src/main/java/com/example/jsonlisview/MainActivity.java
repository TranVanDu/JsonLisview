package com.example.jsonlisview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnLoad;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button) findViewById(R.id.btnJson);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar_1);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                new DownLoadJson().execute("https://jsonplaceholder.typicode.com/users?fbclid=IwAR3Dng6bvKb2HdU36agfWvifAN5_Hq8dUUxqwY9A2Kp4FnSUjQvg-UsThH4");

            }
        });
    }

    private class DownLoadJson extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


        @Override
        protected void onPreExecute() {
            this.progressDialog.setCancelable(true);
            this.progressDialog.setMessage("Đang xử lý, vui lòng chờ đợi");
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setProgress(0);
            this.progressDialog.setMax(100);
            this.progressDialog.show();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... strings) {
            StringBuffer content = new StringBuffer();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = "";
                    long total = 0;
                    while ((line = bufferedReader.readLine()) != null) {
                        total++;
                        publishProgress("" + total);
                        Thread.sleep(10);
                        content.append(line);
                    }
                    bufferedReader.close();
                } else {
                    return null;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            this.progressDialog.setProgress(Integer.parseInt(values[0]));

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            this.progressDialog.dismiss();
            progressBar1.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("bundle", aVoid);
            intent.putExtra("String",bundle);
            startActivity(intent);

        }
    }

}
