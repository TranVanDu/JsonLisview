package com.example.jsonlisview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Json> arrJson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listView = (ListView)findViewById(R.id.lvItem);
        arrJson = new ArrayList<>();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("String");
        String file = bundle.getString("bundle");


        try {
            JSONArray array = new JSONArray(file);

            for(int i =0 ; i< array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                String Name = object.getString("name");
                String Id = object.getString("id");
                String UserName = object.getString("username");
                String Phone = object.getString("phone");
                String Address = object.getString("address");
                String Company = object.getString("company");
                String Website = object.getString("website");
                String Email = object.getString("email");
                
                Json json = new Json(Id,Name,UserName, Email, Phone,Website,Company, Address);
                
                arrJson.add(json);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.item_listview,arrJson);
        listView.setAdapter(customAdapter);
    }
}
