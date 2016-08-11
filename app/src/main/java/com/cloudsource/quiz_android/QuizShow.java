package com.cloudsource.quiz_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cloudsource.quiz_android.model.CategoryModel;
import com.cloudsource.quiz_android.model.QuizModel;

/**
 * Created by ayemyathu on 8/7/16.
 */

public class QuizShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        final ListView lv = (ListView) findViewById(R.id.quizlist);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("categoryName")) {
            String categoryName = intent.getStringExtra("categoryName");

            new RequestQuizJSON(categoryName) {
                //override and handle fetched quiz
                @Override
                protected void onPostExecute(QuizModel qmodel) {

                    QuizListViewAdapter adapter = new QuizListViewAdapter(getApplicationContext(), qmodel.getQuiz());
                    lv.setAdapter(adapter);

                    Log.i("azlog", qmodel.getQuiz().get(0).toString());

                }
            }.execute();

        }

    }
}
