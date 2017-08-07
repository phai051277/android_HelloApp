package com.example.phai0512.helloapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainFragment.MyListener {

    private TextView textView;
    private boolean resultFlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragment追加
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    // interface内のメソッドを実装します。
    @Override
    public void onClickButton(String calcView) {
        Toast.makeText(this, String.format("MainFragmentから%sがクリックされました!", calcView), Toast.LENGTH_SHORT).show();
    }

}
