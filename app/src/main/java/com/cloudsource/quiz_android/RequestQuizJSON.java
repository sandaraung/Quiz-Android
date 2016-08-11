package com.cloudsource.quiz_android;

import android.os.AsyncTask;
import android.util.Log;

import com.cloudsource.quiz_android.model.Category;
import com.cloudsource.quiz_android.model.CategoryModel;
import com.cloudsource.quiz_android.model.Quiz;
import com.cloudsource.quiz_android.model.QuizModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azumag on 2016/08/11.
 */
public class RequestQuizJSON extends AsyncTask<String, Void, QuizModel> {

    private String categoryName = "";
    QuizModel quizModel;


    RequestQuizJSON (String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    protected QuizModel doInBackground(String... strings) {
        JSONObject object = JsonParser.getData(Const.ENDPOINT+"/quizzes/"+categoryName);

        List<Quiz> modelList = new ArrayList<Quiz>();

        if (object != null) {
            try {
                JSONArray jsonArray = object.getJSONArray("quiz");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject innerObject = jsonArray.getJSONObject(i);
                    Quiz quiz = new Quiz();
                    quiz.setId(innerObject.getInt("id"));
                    quiz.setQuestion(innerObject.getString("question"));
                    quiz.setAnswers(innerObject.getJSONArray("answers"));
                    quiz.setCollect(innerObject.getInt("collect"));
                    modelList.add(quiz);
                }

                quizModel = new QuizModel();
                quizModel.setQuiz(modelList);


                return quizModel;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(QuizModel qmodel) {

    }
}
