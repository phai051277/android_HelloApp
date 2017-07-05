package com.example.phai0512.helloapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private boolean resultFlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // addButton
        Button addButton = (Button)findViewById(R.id.button);
        // clearButton
        Button clearButton = (Button)findViewById(R.id.button_clear);
        // textView
        textView = (TextView)findViewById(R.id.text_view);
        // editText
        final EditText edittext = (EditText) findViewById(R.id.edit_text);

        // リスナをボタンに登録(add)
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText() + "\n" + edittext.getText().toString());
                edittext.getEditableText().clear();
            }
        });
        // リスナをボタンに登録(clear)
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                edittext.getEditableText().clear();
            }
        });
    }
}
