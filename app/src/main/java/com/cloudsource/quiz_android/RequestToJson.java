package com.cloudsource.quiz_android;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.cloudsource.quiz_android.JsonParser;
import com.cloudsource.quiz_android.ListviewAdapter;
import com.cloudsource.quiz_android.model.Category;
import com.cloudsource.quiz_android.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestToJson extends AsyncTask<String, Void, Boolean> {
    CategoryModel categorymodel;
    String urlstr;

    public String getUrl() {
        return urlstr;
    }

    public void setUrl(String url) {
        this.urlstr = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(String... strings) {
        JSONObject object = JsonParser.getData(urlstr);

//        Log.i("mylog", "object" + object);

        List<Category> modelList = new ArrayList<Category>();

        if (object != null) {
            try {
                JSONArray jsonArray = object.getJSONArray("category");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject innerObject = jsonArray.getJSONObject(i);
                    Category category;
                    category = new Category();
                    category.setName(innerObject.getString("name"));
                    modelList.add(category);
                }

                categorymodel = new CategoryModel();
                categorymodel.setCategories(modelList);

                String result = categorymodel.getCategories().get(1).getName();
//                Log.i("mylog", result);

                return true;

            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
//        ListviewAdapter adapter = new ListviewAdapter(getApplicationContext(), categorymodel.getCategories());
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }
}