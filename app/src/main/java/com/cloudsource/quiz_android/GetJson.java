package com.cloudsource.quiz_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cloudsource.quiz_android.model.Category;
import com.cloudsource.quiz_android.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetJson extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);

        requestToJson request = new requestToJson() {
            @Override
            protected void onPostExecute(final CategoryModel categoryModel) {
                ListviewAdapter adapter = new ListviewAdapter(getApplicationContext(), categorymodel.getCategories());
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Category category = categoryModel.getCategories().get(i);
                        Intent intent = new Intent(getApplicationContext(),QuizShow.class);
                        intent.putExtra("categoryName", category.getName());
                        startActivity(intent);
                    }
                });
            }
        };
        request.setUrl(Const.ENDPOINT+"/categories/");
        request.execute();
    }

    public class requestToJson extends AsyncTask<String, Void, CategoryModel> {
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
        protected CategoryModel doInBackground(String... strings) {
            JSONObject object = JsonParser.getData(urlstr);

            Log.i("mylog", "object" + object);

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
                    Log.i("mylog", result);

                    return categorymodel;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(CategoryModel cmodel) {

        }
    }
}
