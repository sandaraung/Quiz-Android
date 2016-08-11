package com.cloudsource.quiz_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cloudsource.quiz_android.model.Category;
import com.cloudsource.quiz_android.model.Quiz;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by azumag on 2016/08/11.
 */
public class QuizListViewAdapter extends ArrayAdapter {
    Context context;
    List<Quiz> models;

    public QuizListViewAdapter(Context context, List<Quiz> models) {
        super(context, R.layout.quiz_list, models);
        this.context = context;
        this.models = models;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.quiz_list, parent, false);

        TextView tv = (TextView) convertView.findViewById(R.id.questiontxt);
        tv.setText(models.get(position).getQuestion());

        JSONArray answers = models.get(position).getAnswers();

        TextView a1 = (TextView) convertView.findViewById(R.id.answer1);
        TextView a2 = (TextView) convertView.findViewById(R.id.answer2);
        TextView a3 = (TextView) convertView.findViewById(R.id.answer3);
        TextView a4 = (TextView) convertView.findViewById(R.id.answer4);

        try {
            a1.setText(answers.getString(1));
            a2.setText(answers.getString(2));
            a3.setText(answers.getString(3));
            a4.setText(answers.getString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }
}