package com.cloudsource.quiz_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cloudsource.quiz_android.model.Category;
import com.cloudsource.quiz_android.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetJson extends AppCompatActivity {
    ListView lv;
    String url = "https://blooming-depths-53477.herokuapp.com/categories/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);

        requestToJson request = new requestToJson();
        request.setUrl(url);
        request.execute();
    }


}
