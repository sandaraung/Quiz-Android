package com.cloudsource.quiz_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.cloudsource.quiz_android.model.Category;
import com.cloudsource.quiz_android.model.CategoryModel;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetJson extends AppCompatActivity {
    ListView lv;
    String url = "https://raw.githubusercontent.com/AyeMyaThu/WorkShop-Android/master/category.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);

        requestToJson request = new requestToJson();
        request.execute();
    }

    public class requestToJson extends AsyncTask<String, Void, Boolean> {
        CategoryModel categorymodel;

        @Override
        protected Boolean doInBackground(String... strings) {
            JSONObject object = JsonParser.getData();

            Log.i("mylog", "object" + object);

            List<Category> modelList = new ArrayList<Category>();

            if (object != null) {
                try {
                    JSONArray jsonArray = object.getJSONArray("category");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject innerObject = jsonArray.getJSONObject(i);
//                        Log.i("mylog");
                        Category category;
                        category = new Category();
                        category.setName(innerObject.getString("name"));
                        modelList.add(category);
                    }

                    categorymodel = new CategoryModel();
                    categorymodel.setCategories(modelList);

                    String result = categorymodel.getCategories().get(1).getName();
                    Log.i("mylog", result);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            ListviewAdapter adapter = new ListviewAdapter(getApplicationContext(), categorymodel.getCategories());
            lv.setAdapter(adapter);
        }
    }



    /*public class requestToJson extends AsyncTask<String, String, List<CategoryModel>> {

        @Override
        protected List<CategoryModel> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader  = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine())!=null){
                    buffer.append(line);
                }

                String json = buffer.toString();

                JSONObject object = new JSONObject(json);
                JSONArray array = object.getJSONArray("categories");

                List<CategoryModel> modelList = new ArrayList<>();

                Gson gson = new Gson();
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);

                    CategoryModel model = gson.fromJson(jsonObject.toString(),CategoryModel.class);
                    modelList.add(model);
                }
                return modelList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if (connection != null){
                    connection.disconnect();
                }
                try{
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CategoryModel> models) {
            if (models !=null){
                ListviewAdapter adapter = new ListviewAdapter(getApplicationContext(),models);
                lv.setAdapter(adapter);
            }
        }
    }*/

}
