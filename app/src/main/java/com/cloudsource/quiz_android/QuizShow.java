package com.cloudsource.quiz_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.cloudsource.quiz_android.model.Quiz;
import com.cloudsource.quiz_android.model.QuizModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayemyathu on 8/7/16.
 */

public class QuizShow extends AppCompatActivity {
    String urlquiz = "http://blooming-depths-53477.herokuapp.com/quizzes/";
    TextView question, answerone, answertwo, answerthree, collect;
    Button next;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        question = (TextView) findViewById(R.id.questiontxt);
        answerone = (TextView) findViewById(R.id.answerone);
        answertwo = (TextView) findViewById(R.id.answertwo);
        answerthree = (TextView) findViewById(R.id.answerthree);
        next = (Button) findViewById(R.id.next);

        requestToJson json = new requestToJson();
        json.setUrl(urlquiz);
        json.execute();
    }

    private class requestToJson extends AsyncTask<String, Void, Boolean> {
        QuizModel quizModel = new QuizModel();
        String urlstr;

        public String getUrl() {
            return urlquiz;
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
            ArrayList<Quiz> quizz = new ArrayList<Quiz>();
            try {
                JSONArray array = object.getJSONArray("quiz");

                for (int i = 0; i < array.length(); i++) {
                    Quiz quiz = new Quiz();
                    JSONObject quizJson = array.getJSONObject(i);

//                    JSONObject innerobject = array.getJSONObject(0);

                    int id = quizJson.getInt("id");
                    String q = quizJson.getString("question");
                    JSONArray answers = quizJson.getJSONArray("answers");

                    quiz.setId(id);
                    quiz.setQuestion(q);

//                Log.i("mylog","ans")
                    ArrayList<String> ansersList = new ArrayList<String>();
                    for (int j = 0; j < answers.length(); j++) {
                        String ans = answers.getString(j);
                        ansersList.add(ans);
                    }
                    quiz.setAnswers(ansersList);

                    Log.i("mylog", "q" + q + "ans" + answers);
                    quizz.add(quiz);

                }

                quizModel.setQuiz(quizz);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            question.setText(quizModel.getQuiz().get(1).getQuestion());
        }
    }
}
