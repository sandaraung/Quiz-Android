package com.cloudsource.quiz_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayemyathu on 8/6/16.
 */

public class Quiz {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answers")
    @Expose
    private List<String> answers = new ArrayList<String>();
    @SerializedName("collect")
    @Expose
    private Integer collect;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question
     * The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     *
     * @return
     * The answers
     */
    public List<String> getAnswers() {
        return answers;
    }

    /**
     *
     * @param answers
     * The answers
     */
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    /**
     *
     * @return
     * The collect
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     *
     * @param collect
     * The collect
     */
    public void setCollect(Integer collect) {
        this.collect = collect;
    }
}
