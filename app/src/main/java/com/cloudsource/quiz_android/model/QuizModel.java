package com.cloudsource.quiz_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayemyathu on 8/6/16.
 */

public class QuizModel {
    @SerializedName("quiz")
    @Expose
    private List<Quiz> quiz = new ArrayList<Quiz>();

    /**
     * @return The quiz
     */
    public List<Quiz> getQuiz() {
        return quiz;
    }

    /**
     * @param quiz The quiz
     */
    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

}
