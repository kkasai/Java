package com.example.itcollege.androidsample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                TextView textView = new TextView(getApplicationContext());
                textView.setText(editText.getText().toString());
                textView.setTextColor(Color.BLACK);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.content);
                linearLayout.addView(textView);
                i++;
                System.out.println(i);
            }
        });
    }

}
