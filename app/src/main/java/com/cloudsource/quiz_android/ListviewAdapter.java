package com.cloudsource.quiz_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cloudsource.quiz_android.model.Category;

import java.util.List;

/**
 * Created by ayemyathu on 8/7/16.
 */

public class ListviewAdapter extends ArrayAdapter {
    Context context;
    List<Category> models;

    public ListviewAdapter(Context context, List<Category> models) {
        super(context, R.layout.category, models);
        this.context = context;
        this.models = models;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.category, parent, false);

        TextView tv = (TextView) convertView.findViewById(R.id.categorytxt);
        tv.setText(models.get(position).getName());

        return convertView;
    }
}
