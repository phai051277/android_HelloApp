package com.example.phai0512.helloapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;


/**
 * Created by phai0512 on 2017/08/09.
 */

public class ListActivity extends Activity {

    private TextView dateView, hisView, resView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String history = intent.getStringExtra("history");
        String result = intent.getStringExtra("result");
//        Toast.makeText(this, String.format("日時：%s、内容：%s、結果：%s", date, history, result), Toast.LENGTH_LONG).show();

        //ListViewのセット
        ListView listView = new ListView(this);
        setContentView(listView);
        //データの追加
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adapter.add(String.format("%s %s = %s", date, history, result));
        listView.setAdapter(adapter);

    }

}
