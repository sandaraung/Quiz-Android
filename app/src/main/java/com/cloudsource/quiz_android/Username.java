package com.cloudsource.quiz_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Username extends AppCompatActivity {


    EditText username;
    Button enter;
    String name;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String UserName = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_activity);



        username = (EditText)findViewById(R.id.editText);

        enter = (Button)findViewById(R.id.button);



        name = username.getText().toString();

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES,0);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(UserName, name);
        editor.commit();


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                Intent i = new Intent();
//                startActivity(i);



            }
        });







    }
}
